package pl.umkworkshop.bookstore.outgoing.stockService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class StockServiceConfiguration {
    private final String url;
    private final String path;
    private final Long connectionTimeout;
    private final Long readTimeout;

    public StockServiceConfiguration(@Value("${stock-service.url}") String url,
                                     @Value("${stock-service.path}") String path,
                                     @Value("${stock-service.connect-timeout}") Long connectionTimeout,
                                     @Value("${stock-service.read-timeout}") Long readTimeout) {
        this.url = url;
        this.path = path;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    @Bean
    public RestTemplate stockServiceRestTemplate() {
        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofMillis(connectionTimeout))
                .readTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Long getConnectionTimeout() {
        return connectionTimeout;
    }

    public Long getReadTimeout() {
        return readTimeout;
    }
}

