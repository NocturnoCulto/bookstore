package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class DescriptionStoreConfiguration {

    public DescriptionStoreConfiguration() {

    }

    @Bean
    public RestTemplate descriptionStoreRestTemplate() {
        return null;
    }

}
