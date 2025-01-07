package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;

@Component
public class CoreInformationClient {
    private final RestTemplate coreInformationRestTemplate;
    private final CoreInformationConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(CoreInformationClient.class);

    public CoreInformationClient(RestTemplate coreInformationRestTemplate, CoreInformationConfiguration configuration) {
        this.coreInformationRestTemplate = coreInformationRestTemplate;
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
            logger.error("Request for core information id={} failed. Exception = {}", id, ex.getMessage());
            throw ex;
        }
    }
}
