package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.CoreInformation;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;

@Service
public class CoreInformationService {
    private final CoreInformationClient coreInformationClient;

    public CoreInformationService() {
    }

    public CoreInformation getCoreInformationById(Long id) {
        return null;
    }
}
