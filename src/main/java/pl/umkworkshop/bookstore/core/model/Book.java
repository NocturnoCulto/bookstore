package pl.umkworkshop.bookstore.core.model;

public record Book(Long id,
                   String title,
                   String authorFirstName,
                   String authorLastName,
                   int stock,
                   String shortDescription,
                   String longDescription) {
}