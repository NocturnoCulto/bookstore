package pl.umkworkshop.bookstore.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umkworkshop.bookstore.api.model.BookstoreResponseV1;

@RestController
public class BookstoreApi {

    private final BooksProvider booksProvider;

    public BookstoreApi(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookstoreResponseV1> getBookById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.getBookById(id));
    }
}
