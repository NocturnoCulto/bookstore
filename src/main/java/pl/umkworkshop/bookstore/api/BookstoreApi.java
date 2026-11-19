package pl.umkworkshop.bookstore.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.api.model.request.NewDescriptionV1;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;
import pl.umkworkshop.bookstore.api.model.response.ErrorResponseV1;
import pl.umkworkshop.bookstore.api.model.response.RemovedBookV1;

@RestController
public class BookstoreApi {

    private static final String APPLICATION_JSON_V1 = "application/vnd.bookstore.v1+json";

    private final BooksProvider booksProvider;

    public BookstoreApi(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    public ResponseEntity<BookstoreResponseV1> getAllBooks() {
        return null;
    }

    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return null;
    }

    public ResponseEntity<BookstoreResponseV1> getBooksByAuthorLastName(@RequestParam String lastName) {
        return null;
    }

    public ResponseEntity<BookstoreResponseV1> addBook(@RequestBody BookToAddV1 book) {
        return null;
    }

    public ResponseEntity<?> changeDescription(
            @PathVariable Long id,
            @RequestBody NewDescriptionV1 newDescription
    ) {
        return null;
    }

    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        return null;
    }
}
