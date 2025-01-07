package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.Description;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

@Service
public class DescriptionStoreService {
    private final LoadingCache<Long, DescriptionDTO> descriptionStoreCache;

    public DescriptionStoreService(LoadingCache<Long, DescriptionDTO> descriptionStoreCache) {
        this.descriptionStoreCache = descriptionStoreCache;
    }

    public Description getDescriptionById(Long id) {
        DescriptionDTO descriptionDTO = descriptionStoreCache.get(id);

        if (descriptionDTO == null) {
            return null;
        }
        return new Description(descriptionDTO.shortDescription(), descriptionDTO.longDescription());
    }
}
