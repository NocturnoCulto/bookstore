package pl.umkworkshop.bookstore.core.model;

public record Book(Long id,
                   CoreInformation coreInformation,
                   Stock stock,
                   Description description) {
}