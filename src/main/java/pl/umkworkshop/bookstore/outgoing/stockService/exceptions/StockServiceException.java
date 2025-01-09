package pl.umkworkshop.bookstore.outgoing.stockService.exceptions;

public class StockServiceException extends RuntimeException {
  public StockServiceException(String message) {
    super(message, null, false, false);
  }
}
