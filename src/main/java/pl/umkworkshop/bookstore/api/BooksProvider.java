package pl.umkworkshop.bookstore.api;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.api.model.*;
import pl.umkworkshop.bookstore.core.bookstoreService.BookstoreService;
import pl.umkworkshop.bookstore.core.model.Book;

import java.util.List;


@Service
public class BooksProvider {

    private final BookstoreService bookstoreService;

    public BooksProvider(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public BookstoreResponseV1 getBookById(Long id) {
        return new BookstoreResponseV1(List.of(mapBookToBookV1(bookstoreService.getBookById(id))));
    }

    private BookV1 mapBookToBookV1(Book book) {
        return new BookV1(
                book.id(),
                book.coreInformation().title(),
                new AuthorV1(book.coreInformation().authorFirstName(), book.coreInformation().authorLastName()),
                book.stock().stockCount(),
                new DescriptionV1(book.description().shortDescription(), book.description().longDescription()),
                new PriceV1(book.stock().price().value(), book.stock().price().currency()));
    }
}
