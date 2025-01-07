package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class DescriptionStoreConfiguration {
    private final String url;
    private final String path;
    private final Long connectTimeout;
    private final Long readTimeout;
    private final Long retryReadTimeout;

    public DescriptionStoreConfiguration(
            @Value("${description-store.url}") String url,
            @Value("${description-store.path}") String path,
            @Value("${description-store.connect-timeout}") Long connectTimeout,
            @Value("${description-store.read-timeout}") Long readTimeout,
            @Value("${description-store.retry-read-timeout}") Long retryReadTimeout) {
        this.url = url;
        this.path = path;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.retryReadTimeout = retryReadTimeout;
    }

    @Bean
    public RestTemplate descriptionStoreRestTemplate() {
        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeout))
                .readTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public RestTemplate descriptionStoreRestTemplateForRetry() {
        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeout))
                .readTimeout(Duration.ofMillis(retryReadTimeout))
                .build();
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Long getConnectTimeout() {
        return connectTimeout;
    }

    public Long getReadTimeout() {
        return readTimeout;
    }

    public Long getRetryReadTimeout() {
        return retryReadTimeout;
    }
}
