package pl.umkworkshop.bookstore.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionDTO;

import java.util.concurrent.TimeUnit;

@Configuration
public class DescriptionStoreCacheConfiguration {
    private final Long cacheSize;
    private final Long refreshAfterWrite;
    private final Long expireAfterWrite;
    private final DescriptionStoreClient descriptionStoreClient;

    public DescriptionStoreCacheConfiguration(@Value("${description-store.cache.size}") Long cacheSize,
                                              @Value("${description-store.cache.refresh_after_write}") Long refreshAfterWrite,
                                              @Value("${description-store.cache.expire_after_write}") Long expireAfterWrite,
                                              DescriptionStoreClient descriptionStoreClient) {
        this.cacheSize = cacheSize;
        this.refreshAfterWrite = refreshAfterWrite;
        this.expireAfterWrite = expireAfterWrite;
        this.descriptionStoreClient = descriptionStoreClient;
    }

    @Bean
    LoadingCache<Long, DescriptionDTO> descriptionStoreCache() {
        return Caffeine.newBuilder()
                .maximumSize(cacheSize)
                .refreshAfterWrite(refreshAfterWrite, TimeUnit.SECONDS)
                .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                .build(descriptionStoreClient::getDescriptionById);
    }

    public Long getCacheSize() {
        return cacheSize;
    }

    public Long getRefreshAfterWrite() {
        return refreshAfterWrite;
    }

    public Long getExpireAfterWrite() {
        return expireAfterWrite;
    }
}
