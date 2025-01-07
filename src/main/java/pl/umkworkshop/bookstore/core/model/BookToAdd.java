package pl.umkworkshop.bookstore.core.model;

public record BookToAdd(String title,
                        String authorFirstName,
                        String authorLastName,
                        int stock,
                        String shortDescription,
                        String longDescription,
                        Price price) {
}