package pl.umkworkshop.bookstore.core.bookstoreService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BookstoreConfiguration {

    @Bean
    ExecutorService bookstoreExecutorService() {
        return Executors.newFixedThreadPool(20);
    }
}
