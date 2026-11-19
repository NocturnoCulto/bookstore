package pl.umkworkshop.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV2;
import pl.umkworkshop.bookstore.api.model.response.ErrorResponseV2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookstoreApiV2Test extends BaseTest {

    private static final MediaType APPLICATION_JSON_V2 =
            MediaType.valueOf("application/vnd.bookstore.v2+json");

    @Test
    void shouldGetAllBooksUsingVersionTwoApi() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/allBooks")
                        .accept(APPLICATION_JSON_V2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V2))
                .andReturn();

        BookstoreResponseV2 response = mapFromJson(
                result.getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8),
                BookstoreResponseV2.class);

        assertEquals("2.0", response.apiVersion());
        assertEquals(9, response.books().size());
    }

    @Test
    void shouldGetBookByIdUsingVersionTwoApi() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/books/123")
                        .accept(APPLICATION_JSON_V2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V2))
                .andReturn();

        BookstoreResponseV2 response = mapFromJson(
                result.getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8),
                BookstoreResponseV2.class);

        assertEquals("2.0", response.apiVersion());
        assertEquals(1, response.books().size());
        assertEquals(123L, response.books().get(0).id());
    }

    @Test
    void shouldReturnVersionTwoNotFoundResponse() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/books/999")
                        .accept(APPLICATION_JSON_V2))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_V2))
                .andExpect(jsonPath("$.message")
                        .value("Book with id 999 was not found"))
                .andReturn();

        ErrorResponseV2 response = mapFromJson(
                result.getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8),
                ErrorResponseV2.class);

        assertEquals("2.0", response.apiVersion());
    }
}
