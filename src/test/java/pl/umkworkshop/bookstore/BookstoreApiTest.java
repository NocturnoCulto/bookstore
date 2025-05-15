package pl.umkworkshop.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umkworkshop.bookstore.api.model.BookV1;
import pl.umkworkshop.bookstore.api.model.BookstoreResponseV1;
import pl.umkworkshop.bookstore.outgoing.stockService.exceptions.StockServiceException;

import javax.management.RuntimeErrorException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.Currency;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.*;

public class BookstoreApiTest extends StubBaseTest {

    @Test
    void shouldReturnBookInformation() throws Exception {
        // given
        stubAllServices();
        String uri = "/books/123";

        // when
        MockHttpServletResponse apiResponse = getResponse(uri);

        int status = apiResponse.getStatus();

        BookstoreResponseV1 response = getResponseBody(apiResponse);
        BookV1 book = response.getBooks().getFirst();

        // then
        assertEquals(200, status);
        assertEquals(123, book.id());
        assertEquals("Test Book", book.title());
        assertEquals("Author Name", book.author().authorName());
        assertEquals("Author Last Name", book.author().authorLastName());
        assertEquals(7, book.stock());
        assertEquals(BigDecimal.valueOf(77), book.price().value());
        assertEquals(Currency.getInstance("PLN"), book.price().currency());
        assertEquals("Short text description", book.description().shortDescription());
        assertEquals("Short text description", book.description().shortDescription());
    }

    @Test
    void shouldRetryCoreInformationServiceWhenFirstRequestTimeout() throws Exception {
        // given
        stubAllServices();
        stubCoreInformationServiceWithDelay(200);
        String uri = "/books/123";

        // when
        MockHttpServletResponse apiResponse = getResponse(uri);

        int status = apiResponse.getStatus();

        BookstoreResponseV1 response = getResponseBody(apiResponse);
        BookV1 book = response.getBooks().getFirst();

        // then
        assertEquals(200, status);
        assertEquals(123, book.id());
        assertEquals("Test Book", book.title());

        wireMockServer.verify(2, getRequestedFor(urlPathEqualTo("/bookById/123")));
    }

    @Test
    void shouldReturn503StatusCodeWhenCoreInformationServiceFailed() throws Exception {
        // given
        stubAllServices();
        stubCoreInformationServiceFail();
        String uri = "/books/123";

        // when
        // then
        MockHttpServletResponse response = getResponse(uri);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), response.getStatus());
        assertEquals("Internal server error: Request for core information for id={123} failed", response.getContentAsString());

    }

    @Test
    void shouldCachedDescription() throws Exception {
        //given:
        stubAllServices();
        String uri = "/books/123";

        //when:
        for (int i = 0; i < 10; i++) {
            getResponse(uri);
        }
        BookV1 book = getResponseBodyForUri(uri).getBooks().getFirst();

        //then:
        assertEquals(123, book.id());
        assertEquals("Short text description", book.description().shortDescription());
        assertEquals("Short text description", book.description().shortDescription());

        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo("/descriptionById/123")));
    }

    @Test
    void shouldOpenCircuitBreakerWhenStockServiceFailed() throws Exception {
        assertTrue(false);
    }

    @Test
    void shouldRefreshCache() throws Exception {

        assertTrue(false);
    }



}
