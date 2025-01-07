package pl.umkworkshop.bookstore.core.model;

import java.math.BigDecimal;
import java.util.Currency;

public record Price(BigDecimal value,
                    Currency currency) {
}
