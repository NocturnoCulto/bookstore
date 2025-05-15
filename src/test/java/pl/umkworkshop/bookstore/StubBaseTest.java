package pl.umkworkshop.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;

public abstract class StubBaseTest extends BaseTest {

    protected void stubAllServices()
    {
        // core information service

        stubFor(get(urlPathMatching("/bookById/[0-9]+"))
                .willReturn(aResponse().withBodyFile("coreInformationService/123.json")
                        .withFixedDelay(50)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(200)));

        // stock service

        stubFor(get(urlPathMatching("/stockById/[0-9]+"))
                .willReturn(aResponse().withBodyFile("stockService/123.json")
                        .withFixedDelay(100)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(200)));

        // description store

        stubFor(get(urlPathMatching("/descriptionById/[0-9]+"))
                .willReturn(aResponse().withBodyFile("descriptionStore/123.json")
                        .withFixedDelay(100)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(200)));
    }

    protected void stubCoreInformationServiceWithDelay(int delay) {

    }

    protected void stubCoreInformationServiceFail() {

    }

    protected void stubStockServiceFail() {

    }

    protected void stubCacheScenario() {

    }
}
