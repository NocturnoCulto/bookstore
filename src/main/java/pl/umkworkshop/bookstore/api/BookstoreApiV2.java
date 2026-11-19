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

    @GetMapping(value = "/allBooks", produces = APPLICATION_JSON_V2)
    public ResponseEntity<BookstoreResponseV2> getAllBooks() {
        BookstoreResponseV1 response = booksProvider.getAllBooks();
        return ResponseEntity.ok(
                new BookstoreResponseV2(response.getBooks(), API_VERSION)
        );
    }

    @GetMapping(value = "/books/{id}", produces = APPLICATION_JSON_V2)
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        BookstoreResponseV1 response = booksProvider.getBookById(id);
        if (response.getBooks().isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body(new ErrorResponseV2(
                            "Book with id " + id + " was not found",
                            API_VERSION
                    ));
        }

        return ResponseEntity.ok(
                new BookstoreResponseV2(response.getBooks(), API_VERSION)
        );
    }
}
