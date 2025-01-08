package pl.umkworkshop.bookstore.core.bookstoreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.*;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.CoreInformationService;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.DescriptionStoreService;
import pl.umkworkshop.bookstore.outgoing.stockService.StockService;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@Service
public class BookstoreService {
    private final CoreInformationService coreInformationService;
    private final StockService stockService;
    private final DescriptionStoreService descriptionService;

    private final Logger logger = LoggerFactory.getLogger(BookstoreService.class);

    public BookstoreService(CoreInformationService coreInformationService, StockService stockService, DescriptionStoreService descriptionService) {
        this.coreInformationService = coreInformationService;
        this.stockService = stockService;
        this.descriptionService = descriptionService;
    }


    public Book getBookById(Long id) {
        return getBookByIdAsync(id).join();
    }

    @Async
    public CompletableFuture<Book> getBookByIdAsync(Long id) {
        CompletableFuture<CoreInformation> coreInformationFuture = coreInformationService.getCoreInformationByIdAsync(id);
        CompletableFuture<Stock> stockFuture = stockService.getStockByIdAsync(id);
        CompletableFuture<Description> descriptionFuture = descriptionService.getDescriptionByIdAsync(id);

        return coreInformationFuture.thenCombine(stockFuture, (coreInformation, stock) ->
                new Book(id, coreInformation, stock, null)
        ).thenCombine(descriptionFuture, (book, description) ->
                new Book(book.id(), book.coreInformation(), book.stock(), description)
        );
    }

}