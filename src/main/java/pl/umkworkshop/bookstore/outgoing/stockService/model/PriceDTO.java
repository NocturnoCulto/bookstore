package pl.umkworkshop.bookstore.outgoing.stockService.model;

import java.math.BigDecimal;
import java.util.Currency;

public record PriceDTO(BigDecimal value,
                       Currency currency) {
}
