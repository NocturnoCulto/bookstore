package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.CoreInformation;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;

import java.util.concurrent.CompletableFuture;

@Service
public class CoreInformationService {
    private final CoreInformationClient coreInformationClient;

    public CoreInformationService(CoreInformationClient coreInformationClient) {
        this.coreInformationClient = coreInformationClient;
    }

    public CoreInformation getCoreInformationById(Long id) {
        CoreInformationDTO coreInformationDTO = coreInformationClient.getCoreInformationById(id);
        return new CoreInformation(
                coreInformationDTO.title(),
                coreInformationDTO.authorFirstName(),
                coreInformationDTO.authorLastName());
    }

    @Async
    public CompletableFuture<CoreInformation> getCoreInformationByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            CoreInformationDTO dto = coreInformationClient.getCoreInformationById(id);
            return new CoreInformation(dto.title(), dto.authorFirstName(), dto.authorLastName());
        });
    }
}
