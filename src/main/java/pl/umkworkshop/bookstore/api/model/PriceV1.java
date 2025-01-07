package pl.umkworkshop.bookstore.api.model;

import java.math.BigDecimal;
import java.util.Currency;

public record PriceV1(BigDecimal value,
                      Currency currency) {
}
