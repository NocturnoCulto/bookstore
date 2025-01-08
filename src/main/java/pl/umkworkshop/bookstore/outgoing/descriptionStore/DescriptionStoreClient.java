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

    private final Logger logger = LoggerFactory.getLogger(DescriptionStoreClient.class);

    public DescriptionStoreClient(DescriptionStoreConfiguration configuration,
                                  RestTemplate descriptionStoreRestTemplate) {
        this.configuration = configuration;
        this.descriptionStoreRestTemplate = descriptionStoreRestTemplate;
    }

    DescriptionDTO getDescriptionById(Long id) {
        return null;
    }
}
