package pl.umkworkshop.bookstore.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.api.model.request.NewDescriptionV1;
import pl.umkworkshop.bookstore.api.model.response.BookstoreResponseV1;
import pl.umkworkshop.bookstore.api.model.response.RemovedBookV1;

@RestController
public class BookstoreApi {

    private final BooksProvider booksProvider;

    public BookstoreApi(BooksProvider booksProvider) {
        this.booksProvider = booksProvider;
    }

    @GetMapping("/allBooks")
    public ResponseEntity<BookstoreResponseV1> getAllBooks() {
        return ResponseEntity
                .status(200)
                .body(booksProvider.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookstoreResponseV1> getBookById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.getBookById(id));
    }

    @GetMapping("/books")
    public ResponseEntity<BookstoreResponseV1> getBooksByAuthorLastName(@RequestParam(value = "lastName") String lastName) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.getBooksByAuthorLastName(lastName));
    }

    @PostMapping("/books")
    public ResponseEntity<BookstoreResponseV1> addBook(@RequestBody BookToAddV1 book) {
        return ResponseEntity
                .status(201)
                .body(booksProvider.addBook(book));
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<BookstoreResponseV1> changeDescription(@PathVariable Long id, @RequestBody NewDescriptionV1 newDescription) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.changeDescription(id, newDescription));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<RemovedBookV1> deleteBookById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(booksProvider.deleteBookById(id));
    }
}
