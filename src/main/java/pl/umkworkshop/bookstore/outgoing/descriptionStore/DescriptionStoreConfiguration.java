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
    private final Long connectionTimeout;
    private final Long readTimeout;

    public DescriptionStoreConfiguration(@Value("${description-store.url}") String url,
                                         @Value("${description-store.path}") String path,
                                         @Value("${description-store.connect-timeout}") Long connectionTimeout,
                                         @Value("${description-store.read-timeout}") Long readTimeout) {
        this.url = url;
        this.path = path;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;

    }

    @Bean
    public RestTemplate descriptionStoreRestTemplate() {
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
