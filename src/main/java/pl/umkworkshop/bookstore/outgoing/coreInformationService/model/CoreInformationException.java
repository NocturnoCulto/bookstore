package pl.umkworkshop.bookstore.outgoing.coreInformationService.model;

public class CoreInformationException extends RuntimeException {
    public CoreInformationException(String message) {
        super(message, null, false, false);
    }
}
