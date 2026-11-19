package pl.umkworkshop.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.api.model.request.NewDescriptionV1;
import pl.umkworkshop.bookstore.api.model.response.BookV1;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;
import pl.umkworkshop.bookstore.api.model.response.RemovedBookV1;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookstoreApiTest extends BaseTest {

    private static final MediaType APPLICATION_JSON_V1 =
            MediaType.valueOf("application/vnd.bookstore.v1+json");

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllBooks() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/all-books")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andReturn();

        BookstoreResponseV1 response = mapFromJson(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                BookstoreResponseV1.class);

        assertEquals(9, response.getBooks().size());
        assertEquals(123L, response.getBooks().get(0).id());
        assertEquals(160L, response.getBooks().get(response.getBooks().size() - 1).id());
    }

    @Test
    void shouldGetBookById() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/books/123")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andReturn();

        BookstoreResponseV1 response = mapFromJson(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                BookstoreResponseV1.class);

        assertEquals(1, response.getBooks().size());
        assertEquals(123L, response.getBooks().get(0).id());
        assertEquals("Hobbit", response.getBooks().get(0).title());
    }

    @Test
    void shouldReturnNotFoundWhenBookDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/999")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andExpect(jsonPath("$.message")
                        .value("Book with id 999 was not found"));
    }

    @Test
    void shouldGetBooksByAuthorLastName() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/books")
                        .param("lastName", "Tolkien")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andReturn();

        BookstoreResponseV1 response = mapFromJson(
                result.getResponse().getContentAsString(),
                BookstoreResponseV1.class);

        assertEquals(2, response.getBooks().size());
        assertTrue(response.getBooks().stream()
                .allMatch(book -> "Tolkien".equals(book.author().authorLastName())));
    }

    @Test
    void shouldAddBook() throws Exception {
        BookToAddV1 book = new BookToAddV1(
                "Nowa książka",
                "Jan",
                "Kowalski",
                7,
                "Krótki opis",
                "Długi opis");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(objectMapper.writeValueAsString(book))
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andReturn();

        BookstoreResponseV1 response = mapFromJson(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                BookstoreResponseV1.class);
        BookV1 addedBook = response.getBooks().get(0);

        assertEquals(1, response.getBooks().size());
        assertEquals("Nowa książka", addedBook.title());
        assertEquals("Kowalski", addedBook.author().authorLastName());
        assertEquals(7, addedBook.stock());
    }

    @Test
    void shouldChangeBookDescription() throws Exception {
        NewDescriptionV1 description = new NewDescriptionV1(
                "Zmieniony krótki opis",
                "Zmieniony długi opis");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.patch("/books/125")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(objectMapper.writeValueAsString(description))
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andReturn();

        BookstoreResponseV1 response = mapFromJson(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                BookstoreResponseV1.class);
        BookV1 updatedBook = response.getBooks().get(0);

        assertEquals("Zmieniony krótki opis", updatedBook.description().shortDescription());
        assertEquals("Zmieniony długi opis", updatedBook.description().longDescription());
    }

    @Test
    void shouldDeleteBook() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete("/books/155")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andReturn();

        RemovedBookV1 response = mapFromJson(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                RemovedBookV1.class);

        assertEquals(155L, response.removedId());
        mvc.perform(MockMvcRequestBuilders.get("/books/155")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingMissingBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/books/999")
                        .accept(APPLICATION_JSON_V1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V1))
                .andExpect(jsonPath("$.message")
                        .value("Book with id 999 was not found"));
    }
}
