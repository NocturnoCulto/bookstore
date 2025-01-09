package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.Description;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

@Service
public class DescriptionStoreService {
    private final DescriptionStoreClient descriptionStoreClient;

    public DescriptionStoreService(DescriptionStoreClient descriptionStoreClient) {
        this.descriptionStoreClient = descriptionStoreClient;
    }

    public Description getDescriptionById(Long id) {
        DescriptionDTO descriptionDTO = descriptionStoreClient.getDescriptionById(id);

        return new Description(descriptionDTO.shortDescription(), descriptionDTO.longDescription());
    }
}
