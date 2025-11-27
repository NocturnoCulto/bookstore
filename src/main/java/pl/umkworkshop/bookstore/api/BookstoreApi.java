package pl.umkworkshop.bookstore.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umkworkshop.bookstore.api.model.BookstoreResponseV1;

import java.time.Duration;
import java.time.Instant;

@RestController
public class BookstoreApi {
    private final Logger logger = LoggerFactory.getLogger(BookstoreApi.class);


    private final BooksProvider booksProvider;

    public BookstoreApi(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookstoreResponseV1> getBookById(@PathVariable Long id) {
        Instant start = Instant.now();
        BookstoreResponseV1 bookById = null;
        try {
            bookById = booksProvider.getBookById(id);
        } catch (Exception ignored) {
        }

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        if (bookById == null) {
            logger.info("Failed request for book id={} took {} ms", id, timeElapsed.toMillis());
            return ResponseEntity
                    .status(500)
                    .build();
        }

        logger.info("Request for book id={} took {} ms", id, timeElapsed.toMillis());
        return ResponseEntity
                .status(200)
                .body(bookById);
    }
}
