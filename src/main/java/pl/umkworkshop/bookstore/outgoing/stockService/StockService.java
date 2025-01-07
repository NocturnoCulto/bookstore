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
        StockDTO stockDTO = stockServiceClient.getCoreInformationById(id);
        return new Stock(stockDTO.stockCount(),
                new Price(stockDTO.price().value(), stockDTO.price().currency()));
    }
}
