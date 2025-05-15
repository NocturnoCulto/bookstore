package pl.umkworkshop.bookstore;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.umkworkshop.bookstore.api.model.BookstoreResponseV1;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@ActiveProfiles("integration")
@SpringBootTest(classes = BookstoreApplication.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private LoadingCache<Long, DescriptionDTO> descriptionStoreCache;

    @Autowired
    private CircuitBreaker circuitBreaker;

    WireMockServer wireMockServer = new WireMockServer(8123);

    @BeforeAll
    void startWireMock() {
        wireMockServer.start();
    }

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        configureFor("localhost", 8123);

        descriptionStoreCache.invalidateAll();
        circuitBreaker.transitionToClosedState();

        wireMockServer.resetAll();

    }

    protected MockHttpServletResponse getResponse(String uri) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        return mvcResult.getResponse();
    }

    protected BookstoreResponseV1 getResponseBody(MockHttpServletResponse response) throws Exception {
        return mapFromJson(response.getContentAsString(), BookstoreResponseV1.class);
    }

    protected BookstoreResponseV1 getResponseBodyForUri(String uri) throws Exception {
        return getResponseBody(getResponse(uri));
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
