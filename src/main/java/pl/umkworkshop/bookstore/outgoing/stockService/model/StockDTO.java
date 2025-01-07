package pl.umkworkshop.bookstore.outgoing.stockService.model;

public record StockDTO(Long id,
                       int stockCount,
                       PriceDTO price) {
}
