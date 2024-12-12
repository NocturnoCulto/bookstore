package pl.umkworkshop.bookstore.repository;

import pl.umkworkshop.bookstore.core.model.Book;
import pl.umkworkshop.bookstore.core.model.BookToAdd;

import java.util.List;

public interface BooksRepository {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    List<Book> getBooksByAuthorLastName(String lastName);

    Book addBook(BookToAdd bookToAdd);

    Long removeBook(Long id);

    Book updateDescription(Long id, String shortDescription, String longDescription);

    Book updateStock(Long id, int newStock);

}
