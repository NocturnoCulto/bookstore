package pl.umkworkshop.bookstore.api.model.response;

import java.util.List;

public record BookstoreResponseV2(List<BookV1> books, String apiVersion) {
}
