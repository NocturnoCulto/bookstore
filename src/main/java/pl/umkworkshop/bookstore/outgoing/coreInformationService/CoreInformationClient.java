package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

@Component
public class CoreInformationClient {
    private final RestTemplate coreInformationRestTemplate;
    private final CoreInformationConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(CoreInformationClient.class);

    public CoreInformationClient(RestTemplate coreInformationRestTemplate,
                                 CoreInformationConfiguration configuration) {
        this.coreInformationRestTemplate = coreInformationRestTemplate;
        this.configuration = configuration;
    }

    public CoreInformationDTO getCoreInformationById(Long id) {
        return null;
    }
}
