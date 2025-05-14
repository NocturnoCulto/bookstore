package pl.umkworkshop.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.model.CoreInformationException;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.model.DescriptionStoreException;
import pl.umkworkshop.bookstore.outgoing.stockService.exceptions.CircuitBreakerOpenException;
import pl.umkworkshop.bookstore.outgoing.stockService.exceptions.StockServiceException;

@RestControllerAdvice
public class BookstoreExceptionHandler {

    @ExceptionHandler(StockServiceException.class)
    public ResponseEntity<String> handleStockServiceException(StockServiceException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Internal server error: " + ex.getMessage());
    }

    @ExceptionHandler(CircuitBreakerOpenException.class)
    public ResponseEntity<String> handleCircuitBreakerOpenException(CircuitBreakerOpenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Service temporary unavailable: " + ex.getMessage());
    }

    @ExceptionHandler(CoreInformationException.class)
    public ResponseEntity<String> handleCircuitBreakerOpenException(CoreInformationException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Internal server error: " + ex.getMessage());
    }

    @ExceptionHandler(DescriptionStoreException.class)
    public ResponseEntity<String> handleCircuitBreakerOpenException(DescriptionStoreException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Internal server error: " + ex.getMessage());
    }

}
