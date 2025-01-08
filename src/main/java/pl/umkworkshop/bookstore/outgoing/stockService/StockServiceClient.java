package pl.umkworkshop.bookstore.outgoing.stockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.CoreInformationConfiguration;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;
import pl.umkworkshop.bookstore.outgoing.stockService.model.StockDTO;

@Component
public class StockServiceClient {
    private final RestTemplate stockServiceRestTemplate;
    private final StockServiceConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(StockServiceClient.class);

    public StockServiceClient(RestTemplate stockServiceRestTemplate, StockServiceConfiguration configuration) {
        this.stockServiceRestTemplate = stockServiceRestTemplate;
        this.configuration = configuration;
    }

    public StockDTO getCoreInformationById(Long id) {
        return null;
    }
}
