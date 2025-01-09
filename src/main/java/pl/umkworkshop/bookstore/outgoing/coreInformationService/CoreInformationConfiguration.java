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

    public CoreInformationConfiguration(@Value("${core-information-service.url}") String url,
                                        @Value("${core-information-service.path}") String path,
                                        @Value("${core-information-service.connection-timeout}") Long connectionTimeout,
                                        @Value("${core-information-service.read-timeout}") Long readTimeout) {
        this.url = url;
        this.path = path;
        this.connectionTimeout = connectionTimeout;\
        this.readTimeout = readTimeout;
    }


    @Bean
    public RestTemplate coreInformationRestTemplate() {
        return null;
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
