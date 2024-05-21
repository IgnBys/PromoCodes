package com.sii.promoCodes;
import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import com.sii.promoCodes.Services.DiscountResultService;
import com.sii.promoCodes.Services.ProductService;
import com.sii.promoCodes.Services.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseTests {
    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private PurchaseService purchaseService;

    @InjectMocks
    private DiscountResultService discountResultService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllPurchases(){
        Purchase purchase1 = new Purchase(1L,
                LocalDateTime.now(),
                BigDecimal.valueOf(45.55),
                BigDecimal.valueOf(20.00),
                BigDecimal.valueOf(25.55),
                null);
        Purchase purchase2 = new Purchase(2L,
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(25.55),
                null);
        Purchase purchase3 = new Purchase(3L,
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(25.55),
                "Currency mismatch");

        List<Purchase> allPurchases = Arrays.asList(purchase3, purchase1, purchase2);
        when(purchaseRepository.findAll()).thenReturn(allPurchases);
        List<Purchase> listPurchases = purchaseService.getAllPurchases();
        assertEquals(3,listPurchases.size());
    }



    @Test
    void shouldCreatePurchaseWithoutUsingPromoCode(){
        final Product product = new Product(1L,
                "Apple",
                BigDecimal.valueOf(25.55),
                "PLN",
                null);
        Purchase expectedPurchase = new Purchase(null,
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(25.55),
                null);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(purchaseRepository.save(expectedPurchase)).thenReturn(expectedPurchase);
        Purchase createdPurchase = discountResultService.simulatePurchaseWithoutPromoCode(1L);
//        assertEquals(expectedPurchase, createdPurchase);

        assertEquals(expectedPurchase.getId(), createdPurchase.getId());
        assertEquals(expectedPurchase.getWarning(), createdPurchase.getWarning());
        assertEquals(expectedPurchase.getRegularPrice(), createdPurchase.getRegularPrice());
        assertEquals(expectedPurchase.getDiscountAmount(), createdPurchase.getDiscountAmount());
        assertEquals(expectedPurchase.getFinalPrice(), createdPurchase.getFinalPrice());
        assertEquals(expectedPurchase.getPurchaseDate().toLocalDate(), createdPurchase.getPurchaseDate().toLocalDate());
        assertEquals(expectedPurchase.getPurchaseDate().toLocalTime().withNano(0), createdPurchase.getPurchaseDate().toLocalTime().withNano(0));
    }





}
