package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.Description;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class DescriptionStoreService {
    private final LoadingCache<Long, DescriptionDTO> descriptionStoreCache;
    private final ExecutorService bookstoreExecutorService;

    public DescriptionStoreService(LoadingCache<Long, DescriptionDTO> descriptionStoreCache, ExecutorService bookstoreExecutorService) {
        this.descriptionStoreCache = descriptionStoreCache;
        this.bookstoreExecutorService = bookstoreExecutorService;
    }

    public Description getDescriptionById(Long id) {
        DescriptionDTO descriptionDTO = descriptionStoreCache.get(id);

        if (descriptionDTO == null) {
            return null;
        }
        return new Description(descriptionDTO.shortDescription(), descriptionDTO.longDescription());
    }

    @Async
    public CompletableFuture<Description> getDescriptionByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            DescriptionDTO descriptionDTO = descriptionStoreCache.get(id);
            return new Description(descriptionDTO.shortDescription(), descriptionDTO.longDescription());
        }, bookstoreExecutorService);
    }
}
