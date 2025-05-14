package pl.umkworkshop.bookstore.outgoing.descriptionStore.model;

public class DescriptionStoreException extends RuntimeException {
    public DescriptionStoreException(String message) {
        super(message, null, false, false);
    }
}
