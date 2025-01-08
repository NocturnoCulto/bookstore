package pl.umkworkshop.bookstore.outgoing.stockService;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.Price;
import pl.umkworkshop.bookstore.core.model.Stock;
import pl.umkworkshop.bookstore.outgoing.stockService.model.StockDTO;


@Service
public class StockService {
    private final StockServiceClient stockServiceClient;

    public StockService(StockServiceClient stockServiceClient) {
        this.stockServiceClient = stockServiceClient;
    }

    public Stock getCoreInformationById(Long id) {
        return null;
    }
}
