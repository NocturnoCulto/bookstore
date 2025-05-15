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

        assertTrue(false);

    }

    @Test
    void shouldRetryCoreInformationServiceWhenFirstRequestTimeout() throws Exception {

        assertTrue(false);

    }

    @Test
    void shouldReturn503StatusCodeWhenCoreInformationServiceFailed() throws Exception {

        assertTrue(false);

    }

    @Test
    void shouldCachedDescription() throws Exception {

        assertTrue(false);

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
