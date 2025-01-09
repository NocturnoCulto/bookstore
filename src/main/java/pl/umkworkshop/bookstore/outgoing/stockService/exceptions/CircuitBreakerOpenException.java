package pl.umkworkshop.bookstore.outgoing.stockService.exceptions;

public class CircuitBreakerOpenException extends RuntimeException {
  public CircuitBreakerOpenException(String message) {
    super(message, null, false, false);
  }
}
