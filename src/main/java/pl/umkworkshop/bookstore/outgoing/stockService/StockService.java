package pl.umkworkshop.bookstore.outgoing.stockService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.Price;
import pl.umkworkshop.bookstore.core.model.Stock;
import pl.umkworkshop.bookstore.outgoing.stockService.model.StockDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;


@Service
public class StockService {
    private final StockServiceClient stockServiceClient;
    private final ExecutorService bookstoreExecutorService;

    public StockService(StockServiceClient stockServiceClient, ExecutorService bookstoreExecutorService) {
        this.stockServiceClient = stockServiceClient;
        this.bookstoreExecutorService = bookstoreExecutorService;
    }

    public Stock getStockById(Long id) {
        StockDTO stockDTO = stockServiceClient.getStockById(id);
        return new Stock(stockDTO.stockCount(),
                new Price(stockDTO.price().value(), stockDTO.price().currency()));
    }

    @Async
    public CompletableFuture<Stock> getStockByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            StockDTO stockDTO = stockServiceClient.getStockById(id);
            return new Stock(stockDTO.stockCount(),
                    new Price(stockDTO.price().value(), stockDTO.price().currency()));
        }, bookstoreExecutorService);
    }
}
