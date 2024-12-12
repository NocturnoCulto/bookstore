package pl.umkworkshop.bookstore.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;
import pl.umkworkshop.bookstore.api.model.response.RemovedBookV1;

@RestController
public class BookstoreApi {

    private final BooksProvider booksProvider;

    public BookstoreApi(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    public ResponseEntity<BookstoreResponseV1> getAllBooks() {
        return null;
    }

    public ResponseEntity<BookstoreResponseV1> getBookById() {
        return null;
    }

    public ResponseEntity<BookstoreResponseV1> getBooksByAuthorLastName() {
        return null;
    }

    public ResponseEntity<BookstoreResponseV1> addBook() {
        return null;
    }

    public ResponseEntity<BookstoreResponseV1> changeDescription() {
        return null;
    }

    public ResponseEntity<RemovedBookV1> deleteBookById() {
        return null;
    }
}
