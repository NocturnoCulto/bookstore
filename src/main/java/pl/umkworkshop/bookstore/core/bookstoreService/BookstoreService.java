package pl.umkworkshop.bookstore.core.bookstoreService;

import org.springframework.stereotype.Service;
import pl.umkworkshop.bookstore.core.model.*;
import pl.umkworkshop.bookstore.outgoing.coreInformationService.CoreInformationService;
import pl.umkworkshop.bookstore.outgoing.descriptionStore.DescriptionStoreService;
import pl.umkworkshop.bookstore.outgoing.stockService.StockService;

@Service
public class BookstoreService {
    private final CoreInformationService coreInformationService;
    private final StockService stockService;
    private final DescriptionStoreService descriptionService;

    public BookstoreService(CoreInformationService coreInformationService, StockService stockService, DescriptionStoreService descriptionService) {
        this.coreInformationService = coreInformationService;
        this.stockService = stockService;
        this.descriptionService = descriptionService;
    }


    public Book getBookById(Long id) {
        CoreInformation coreInformation = coreInformationService.getCoreInformationById(id);
        Stock stock = stockService.getCoreInformationById(id);
        Description description = descriptionService.getDescriptionById(id);

        return new Book(id, coreInformation, stock, description);
    }

}