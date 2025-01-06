package pl.umkworkshop.bookstore.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;
import pl.umkworkshop.bookstore.api.model.response.RemovedBookV1;

@RestController
public class BookstoreApi {

    private final BooksProvider booksProvider;

    public BookstoreApi(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    @GetMapping(value = "/allBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookstoreResponseV1> getAllBooks() {

        return ResponseEntity
                .status(200)
                .body(booksProvider.getAllBooks());
    }

    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookstoreResponseV1> getBookById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.getBookById(id));
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookstoreResponseV1> getBooksByAuthorLastName(@RequestParam String lastName) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.getBooksByAuthorLastName(lastName));
    }

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookstoreResponseV1> addBook(@RequestBody BookToAddV1 book) {
        return ResponseEntity
                .status(201)
                .body(booksProvider.addBook(book));
    }

    public ResponseEntity<BookstoreResponseV1> changeDescription() {
        return null;
    }

    public ResponseEntity<RemovedBookV1> deleteBookById() {
        return null;
    }
}
