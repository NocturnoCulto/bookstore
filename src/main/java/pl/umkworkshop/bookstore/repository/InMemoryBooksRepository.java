package pl.umkworkshop.bookstore.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import pl.umkworkshop.bookstore.core.model.Book;
import pl.umkworkshop.bookstore.core.model.BookToAdd;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryBooksRepository implements BooksRepository {

    private final Map<Long, Book> booksMap = new HashMap<>();

    @Override
    public List<Book> getAllBooks() {
        return booksMap.values().stream().toList();
    }

    @Override
    public Book getBookById(Long id) {
        return booksMap.get(id);
    }
    
    @Override
    public List<Book> getBooksByAuthorLastName(String lastName) {
        return booksMap.values().stream().filter(book -> book.authorLastName().equals(lastName)).toList();
    }

    @Override
    public Book addBook(BookToAdd bookToAdd) {
        Long newKey = Collections.max(booksMap.keySet()) + 1;
        Book newBook = new Book(newKey, bookToAdd.title(), bookToAdd.authorFirstName(), bookToAdd.authorLastName(), bookToAdd.stock(), bookToAdd.shortDescription(), bookToAdd.longDescription());
        booksMap.put(newBook.id(), newBook);
        return newBook;
    }

    @Override
    public Long removeBook(Long id) {
        if (booksMap.get(id) == null) throw new IllegalArgumentException("Book with id: " + id + " does not exist");
        else {
            booksMap.remove(id);
            return id;
        }
    }

    @Override
    public Book updateDescription(Long id, String shortDescription, String longDescription) {
        if (booksMap.get(id) != null) {
            Book book = booksMap.get(id);
            Book updatedBook = new Book(book.id(), book.title(), book.authorFirstName(), book.authorLastName(), book.stock(), shortDescription, longDescription);
            booksMap.put(id, updatedBook);
            return updatedBook;
        }
        throw new IllegalArgumentException("Book with id: " + id + " does not exist");
    }

    @Override
    public Book updateStock(Long id, int newStock) {
        if (booksMap.get(id) != null) {
            Book book = booksMap.get(id);
            Book updatedBook = new Book(book.id(), book.title(), book.authorFirstName(), book.authorLastName(), newStock, book.shortDescription(), book.longDescription());
            booksMap.put(id, updatedBook);
            return updatedBook;
        }
        throw new IllegalArgumentException("Book with id: " + id + " does not exist");
    }

    @PostConstruct
    void fillBookstoreRepository() {
        booksMap.put(123L, new Book(123L, "Hobbit", "John Ronald Reuel", "Tolkien", 10, "Short description of Hobbit", "Long description of Hobbit"));
        booksMap.put(125L, new Book(125L, "Lord of the rings", "John Ronald Reuel", "Tolkien", 10, "Short description of Lord of the rings", "Long description of Lord of the rings"));
        booksMap.put(130L, new Book(130L, "Potop", "Henryk", "Sienkiewicz", 10, "Short description of Potop", "Long description of Potop"));
        booksMap.put(135L, new Book(135L, "Ogniem i mieczem", "Henryk", "Sienkiewicz", 2, "Short description of Ogniem i mieczem", "Long description of Ogniem i mieczem"));
        booksMap.put(140L, new Book(140L, "Krew elfów", "Andrzej", "Sapkowski", 10, "Short description of Krew elfów", "Long description of Krew elfów"));
        booksMap.put(145L, new Book(145L, "Czas pogardy", "Andrzej", "Sapkowski", 10, "Short description of Czas pogardy", "Long description of Czas pogardy"));
        booksMap.put(150L, new Book(150L, "Chrzest ognia", "Andrzej", "Sapkowski", 10, "Short description of Chrzest ognia", "Long description of Chrzest ognia"));
        booksMap.put(155L, new Book(155L, "Wieża Jaskółki", "Andrzej", "Sapkowski", 10, "Short description of Wieża Jaskółki", "Long description of Wieża Jaskółki"));
        booksMap.put(160L, new Book(160L, "Pani Jeziora", "Andrzej", "Sapkowski", 10, "Short description of Pani Jeziora", "Long description of Pani Jeziora"));
    }
}
