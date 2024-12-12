package pl.umkworkshop.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookstoreApiTest extends BaseTest {

    @Test
    void getAllBooksTest() throws Exception {
        // given
        String uri = "/books";

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        BookstoreResponseV1 response = mapFromJson(contentAsString, BookstoreResponseV1.class);


        // then
        assertEquals(200, status);
        assertEquals(2, response.getBooks().size());
    }
}
