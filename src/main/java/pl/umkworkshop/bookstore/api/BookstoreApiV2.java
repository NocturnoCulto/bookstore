package pl.umkworkshop.bookstore.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV2;
import pl.umkworkshop.bookstore.api.model.response.ErrorResponseV2;

@RestController
public class BookstoreApiV2 {

    private static final String APPLICATION_JSON_V2 = "application/vnd.bookstore.v2+json";
    private static final String API_VERSION = "2.0";

    private final BooksProvider booksProvider;

    public BookstoreApiV2(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    public ResponseEntity<BookstoreResponseV2> getAllBooks() {
        return null;
    }

    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return null;
    }
}
