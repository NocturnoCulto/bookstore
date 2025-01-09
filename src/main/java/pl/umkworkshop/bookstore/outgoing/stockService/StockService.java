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
    private final ExecutorService bookstoreThreadPool;

    public StockService(StockServiceClient stockServiceClient, ExecutorService bookstoreThreadPool) {
        this.stockServiceClient = stockServiceClient;
        this.bookstoreThreadPool = bookstoreThreadPool;
    }

    public Stock getCoreInformationById(Long id) {
        StockDTO stockDTO = stockServiceClient.getStockById(id);
        return new Stock(stockDTO.stockCount(),
                new Price(stockDTO.price().value(), stockDTO.price().currency()));
    }

    @Async
    public CompletableFuture<Stock> getStockByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            StockDTO dto = stockServiceClient.getStockById(id);
            return new Stock(dto.stockCount(), new Price(dto.price().value(), dto.price().currency()));
        }, bookstoreThreadPool);
    }
}
