package pl.umkworkshop.bookstore.core.bookstoreService;

import io.micrometer.observation.ObservationFilter;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.api.model.request.BookToAddV1;
import pl.umkworkshop.bookstore.core.model.Book;
import pl.umkworkshop.bookstore.core.model.BookToAdd;
import pl.umkworkshop.bookstore.repository.BooksRepository;

import java.util.List;

@Service
public class BookstoreService {
    private final BooksRepository booksRepository;

    public BookstoreService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks() {
        return booksRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return booksRepository.getBookById(id);
    }

    public List<Book> getBooksByAuthorLastName(String lastName) {
        return booksRepository.getBooksByAuthorLastName(lastName);
    }

    public Long removeBookById(Long id) {
        return booksRepository.removeBook(id);
    }

    public Book addBook(BookToAdd book) {
        return booksRepository.addBook(book);
    }

    public Book updateDescription(Long id, String shortDescription, String longDescription) {
        return booksRepository.updateDescription(id, shortDescription, longDescription);
    }
}