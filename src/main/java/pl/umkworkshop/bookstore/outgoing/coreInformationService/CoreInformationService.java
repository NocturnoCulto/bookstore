package pl.umkworkshop.bookstore.outgoing.coreInformationService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.CoreInformation;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class CoreInformationService {
    private final CoreInformationClient coreInformationClient;
    private final ExecutorService bookstoreExecutorService;

    public CoreInformationService(CoreInformationClient coreInformationClient, ExecutorService bookstoreExecutorService) {
        this.coreInformationClient = coreInformationClient;
        this.bookstoreExecutorService = bookstoreExecutorService;
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
            CoreInformationDTO coreInformationDTO = coreInformationClient.getCoreInformationById(id);
            return new CoreInformation(
                    coreInformationDTO.title(),
                    coreInformationDTO.authorFirstName(),
                    coreInformationDTO.authorLastName());
        }, bookstoreExecutorService);
    }
}
