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
    private final RestTemplate coreInformationRetryRestTemplate;
    private final CoreInformationConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(CoreInformationClient.class);

    public CoreInformationClient(RestTemplate coreInformationRestTemplate,
                                 RestTemplate coreInformationRetryRestTemplate,
                                 CoreInformationConfiguration configuration) {
        this.coreInformationRestTemplate = coreInformationRestTemplate;
        this.coreInformationRetryRestTemplate = coreInformationRetryRestTemplate;
        this.configuration = configuration;
    }

    public CoreInformationDTO getCoreInformationById(Long id) {
        String uriString = UriComponentsBuilder.fromUriString(configuration.getUrl())
                .path(String.format(configuration.getPath(), id))
                .build()
                .toUriString();

        try {
            return coreInformationRestTemplate.getForObject(uriString, CoreInformationDTO.class);
        } catch (Exception ex) {
            try {
                logger.warn("Retry request for core information for book id={}", id);
                return coreInformationRetryRestTemplate.getForObject(uriString, CoreInformationDTO.class);
            } catch (Exception ex2) {
                logger.error("Retry request for core information for book id={} failed. ex={}",id, ex2.getMessage());
                throw ex2;
            }
        }
    }
}
