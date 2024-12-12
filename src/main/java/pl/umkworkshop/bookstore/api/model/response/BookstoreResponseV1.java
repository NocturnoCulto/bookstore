package pl.umkworkshop.bookstore.api.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookstoreResponseV1 {
    private final List<BookV1> books;

    @JsonCreator
    public BookstoreResponseV1(
            @JsonProperty("books") List<BookV1> books
    ) {
        this.books = books;
    }

    public List<BookV1> getBooks() {
        return books;
    }
}
