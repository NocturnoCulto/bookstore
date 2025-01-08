package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

@Component
public class DescriptionStoreClient {
    private final DescriptionStoreConfiguration configuration;
    private final RestTemplate descriptionStoreRestTemplate;
    private final RestTemplate descriptionStoreRestTemplateForRetry;

    private final Logger logger = LoggerFactory.getLogger(DescriptionStoreClient.class);

    public DescriptionStoreClient(DescriptionStoreConfiguration configuration,
                                  RestTemplate descriptionStoreRestTemplate,
                                  RestTemplate descriptionStoreRestTemplateForRetry) {
        this.configuration = configuration;
        this.descriptionStoreRestTemplate = descriptionStoreRestTemplate;
        this.descriptionStoreRestTemplateForRetry = descriptionStoreRestTemplateForRetry;
    }

    DescriptionDTO getDescriptionById(Long id) {
        String uriString = UriComponentsBuilder.fromUriString(configuration.getUrl())
                .path(String.format(configuration.getPath(), id))
                .build()
                .toUriString();

        try {
            return descriptionStoreRestTemplate.getForObject(uriString, DescriptionDTO.class);
        } catch (Exception ex) {
            try {
                logger.warn("Retry request for description for book id={} after exception = {}", id, ex.getMessage());
                return descriptionStoreRestTemplateForRetry.getForObject(uriString, DescriptionDTO.class);
            } catch (Exception ex2) {
                logger.error("Retry request for description for book id={} failed. Exception = {}", id, ex2.getMessage());
                throw ex2;
            }
        }
    }
}
