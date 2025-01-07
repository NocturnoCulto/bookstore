package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.CoreInformation;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;

@Service
public class CoreInformationService {
    private final CoreInformationClient coreInformationClient;

    public CoreInformationService(CoreInformationClient coreInformationClient) {
        this.coreInformationClient = coreInformationClient;
    }

    public CoreInformation getCoreInformationById(Long id) {
        return new CoreInformation(
                coreInformationClient.getCoreInformationById(id).title(),
                coreInformationClient.getCoreInformationById(id).authorFirstName(),
                coreInformationClient.getCoreInformationById(id).authorLastName());
    }
}
