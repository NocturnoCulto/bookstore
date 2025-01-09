package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class CoreInformationConfiguration {
    private final String url;
    private final String path;
    private final Long connectionTimeout;
    private final Long readTimeout;
    private final Long retryReadTimeout;

    public CoreInformationConfiguration(@Value("${core-information-service.url}") String url,
                                        @Value("${core-information-service.path}") String path,
                                        @Value("${core-information-service.connect-timeout}") Long connectionTimeout,
                                        @Value("${core-information-service.read-timeout}") Long readTimeout,
                                        @Value("${core-information-service.retry-read-timeout}") Long retryReadTimeout) {
        this.url = url;
        this.path = path;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.retryReadTimeout = retryReadTimeout;
    }


    @Bean
    public RestTemplate coreInformationRestTemplate() {
        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofMillis(connectionTimeout))
                .readTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public RestTemplate coreInformationRetryRestTemplate() {
        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofMillis(connectionTimeout))
                .readTimeout(Duration.ofMillis(retryReadTimeout))
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

    public Long getRetryReadTimeout() {
        return retryReadTimeout;
    }
}
