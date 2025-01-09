package pl.umkworkshop.bookstore.core.bookstoreService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class BookstoreConfiguration {

    @Bean
    public ExecutorService bookstoreThreadPool() {
        return Executors.newFixedThreadPool(100);
    }
}
