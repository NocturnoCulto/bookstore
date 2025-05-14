package pl.umkworkshop.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;

public abstract class StubBaseTest extends BaseTest {
    // This class is intentionally empty. It serves as a base test class for other test classes.
    // You can add common test setup or utility methods here if needed.

    @BeforeEach
    protected void resetAllStubs() {
        wireMockServer.resetAll();
        wireMockServer.resetScenarios();
    }

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
        stubFor(get(urlPathMatching("/bookById/[0-9]+"))
                .willReturn(aResponse().withBodyFile("coreInformationService/123.json")
                        .withFixedDelay(delay)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(200)));
    }

    protected void stubCoreInformationServiceFail() {
        stubFor(get(urlPathMatching("/bookById/[0-9]+"))
                .willReturn(aResponse().withBodyFile(null)
                        .withFixedDelay(100)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(503)));
    }

    protected void stubStockServiceFail() {
        stubFor(get(urlPathMatching("/stockById/[0-9]+"))
                .willReturn(aResponse().withBodyFile(null)
                        .withFixedDelay(10)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(503)));
    }

    protected void stubCacheScenario() {
        stubFor(get(urlPathMatching("/descriptionById/[0-9]+")).inScenario("cacheScenario")
                .whenScenarioStateIs(STARTED)
                .willReturn(aResponse().withBodyFile("descriptionStore/123.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(200))
                .willSetStateTo("afterFirstResponse"));

        stubFor(get(urlPathMatching("/descriptionById/[0-9]+")).inScenario("cacheScenario")
                .whenScenarioStateIs("afterFirstResponse")
                .willReturn(aResponse().withBodyFile("descriptionStore/123-update.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withStatus(200)));

    }
}
