package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class CoreInformationConfiguration {

    public CoreInformationConfiguration() {

    }

    @Bean
    public RestTemplate coreInformationRestTemplate() {
        return null;
    }
}
