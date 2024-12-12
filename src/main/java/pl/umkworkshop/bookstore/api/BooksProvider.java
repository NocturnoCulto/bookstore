package pl.umkworkshop.bookstore.api;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.api.model.request.NewDescriptionV1;
import pl.umkworkshop.bookstore.api.model.response.*;
import pl.umkworkshop.bookstore.core.bookstoreService.BookstoreService;
import pl.umkworkshop.bookstore.core.model.Book;
import pl.umkworkshop.bookstore.core.model.BookToAdd;

import java.util.List;


@Service
public class BooksProvider {

    private final BookstoreService bookstoreService;

    public BooksProvider(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public BookstoreResponseV1 getAllBooks() {
        return new BookstoreResponseV1(bookstoreService.getAllBooks()
                .stream()
                .map(this::mapBookToBookV1)
                .sorted()
                .toList());
    }

    public BookstoreResponseV1 getBookById(Long id) {
        return new BookstoreResponseV1(List.of(mapBookToBookV1(bookstoreService.getBookById(id))));
    }

    public BookstoreResponseV1 getBooksByAuthorLastName(String lastName) {
        return new BookstoreResponseV1(bookstoreService.getBooksByAuthorLastName(lastName)
                .stream()
                .map(this::mapBookToBookV1)
                .sorted()
                .toList());
    }

    public BookstoreResponseV1 addBook(BookToAddV1 book) {
        BookToAdd bookToAdd = new BookToAdd(book.title(), book.authorFirstName(), book.authorLastName(), book.stock(), book.shortDescription(), book.longDescription());
        return new BookstoreResponseV1(List.of(mapBookToBookV1(bookstoreService.addBook(bookToAdd))));
    }

    public RemovedBookV1 deleteBookById(Long id) {
        return new RemovedBookV1(bookstoreService.removeBookById(id));

    }

    public BookstoreResponseV1 changeDescription(Long id, NewDescriptionV1 newDescription) {
        return new BookstoreResponseV1(List.of(mapBookToBookV1(bookstoreService.updateDescription(id, newDescription.shortDescription(), newDescription.longDescription()))));
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
