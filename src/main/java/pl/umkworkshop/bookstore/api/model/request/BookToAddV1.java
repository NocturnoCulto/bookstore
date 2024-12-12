package pl.umkworkshop.bookstore.api.model.request;

public record BookToAddV1(String title,
                          String authorFirstName,
                          String authorLastName,
                          int stock,
                          String shortDescription,
                          String longDescription) {
}