package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.Description;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

@Service
public class DescriptionStoreService {
    private final DescriptionStoreClient descriptionStoreClient;

    public DescriptionStoreService() {
    }

    public Description getDescriptionById(Long id) {

        return null;
    }
}
