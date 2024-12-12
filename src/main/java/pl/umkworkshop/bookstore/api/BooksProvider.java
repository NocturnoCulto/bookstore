package pl.umkworkshop.bookstore.api;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.api.model.request.NewDescriptionV1;
import pl.umkworkshop.bookstore.api.model.response.*;
import pl.umkworkshop.bookstore.core.bookstoreService.BookstoreService;
import pl.umkworkshop.bookstore.core.model.Book;


@Service
public class BooksProvider {

    private final BookstoreService bookstoreService;

    public BooksProvider(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public BookstoreResponseV1 getAllBooks() {
        return null;
    }

    public BookstoreResponseV1 getBookById(Long id) {
        return null;
    }

    public BookstoreResponseV1 getBooksByAuthorLastName(String lastName) {
        return null;
    }

    public BookstoreResponseV1 addBook(BookToAddV1 book) {
        return null;
    }

    public RemovedBookV1 deleteBookById(Long id) {
        return null;
    }

    public BookstoreResponseV1 changeDescription(Long id, NewDescriptionV1 newDescription) {
        return null;
    }

    private BookV1 mapBookToBookV1(Book book) {
        return new BookV1(
                book.id(),
                book.title(),
                new AuthorV1(book.authorFirstName(), book.authorLastName()),
                book.stock(),
                new DescriptionV1(book.shortDescription(), book.longDescription()));
    }
}
