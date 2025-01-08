package pl.umkworkshop.bookstore.outgoing.stockService;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umkworkshop.bookstore.outgoing.stockService.exceptions.CircuitBreakerOpenException;
import pl.umkworkshop.bookstore.outgoing.stockService.exceptions.StockServiceException;
import pl.umkworkshop.bookstore.outgoing.stockService.model.StockDTO;

import java.util.function.Supplier;

@Component
public class StockServiceClient {
    private final RestTemplate stockServiceRestTemplate;
    private final StockServiceConfiguration configuration;
    private final CircuitBreaker stockServiceCircuitBreaker;

    private final Logger logger = LoggerFactory.getLogger(StockServiceClient.class);

    public StockServiceClient(RestTemplate stockServiceRestTemplate, StockServiceConfiguration configuration, CircuitBreaker stockServiceCircuitBreaker) {
        this.stockServiceRestTemplate = stockServiceRestTemplate;
        this.configuration = configuration;
        this.stockServiceCircuitBreaker = stockServiceCircuitBreaker;
    }

    public StockDTO getStockById(Long id) {
        Supplier<StockDTO> stockSupplier = CircuitBreaker.decorateSupplier(stockServiceCircuitBreaker, () -> serviceCall(id));
        try {
            return stockSupplier.get();
        } catch (CallNotPermittedException ex) {
            throw new CircuitBreakerOpenException("Circuit breaker is open for stock service");
        }
    }

    private StockDTO serviceCall(Long id) {
        String uriString = UriComponentsBuilder.fromUriString(configuration.getUrl())
                .path(String.format(configuration.getPath(), id))
                .build()
                .toUriString();

        try {
            return stockServiceRestTemplate.getForObject(uriString, StockDTO.class);
        } catch (Exception ex) {
            throw new StockServiceException("Request for stockCount information id={" + id + "} failed");
        }
    }
}
