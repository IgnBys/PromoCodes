package com.sii.promoCodes;

import com.sii.promoCodes.Models.DiscountResult;
import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Services.DiscountResultService;
import com.sii.promoCodes.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountResultTests {

    @Mock
    private ProductService productService;

    @Mock
    private PromoCodeRepository promoCodeRepository;

    @InjectMocks
    private DiscountResultService discountResultService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnDiscountedProduct() {
        Product product = new Product(1L,
                "Apple", BigDecimal.valueOf(55.55),
                "PLN",
                null);
        final PromoCode promoCode = new PromoCode(1L,
                "SUMMER2024",
                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                "PLN",
                5,
                0);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(promoCodeRepository.findByCode("SUMMER2024")).thenReturn(promoCode);
        DiscountResult discountResult = discountResultService.discountProduct(1L, "SUMMER2024");
        assertEquals(BigDecimal.valueOf(30.00).setScale(2), discountResult.getResultPrice().setScale(2));
    }

    @Test
    void shouldReturnWarningForExpiredPromoCode() {
        Product product = new Product(1L,
                "Apple", BigDecimal.valueOf(55.55),
                "PLN",
                null);
        final PromoCode promoCode = new PromoCode(1L,
                "SUMMER2024",
                LocalDateTime.parse("2024-05-14T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                "PLN",
                5,
                0);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(promoCodeRepository.findByCode("SUMMER2024")).thenReturn(promoCode);
        DiscountResult discountResult = discountResultService.discountProduct(1L, "SUMMER2024");
        assertEquals(discountResult.getWarning(), "Promo code expired");
    }


    @Test
    void shouldReturnWarningForCurrencyMismatch() {
        Product product = new Product(1L,
                "Apple", BigDecimal.valueOf(55.55),
                "PLN",
                null);
        final PromoCode promoCode = new PromoCode(1L,
                "SUMMER2024",
                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                "EUR",
                5,
                0);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(promoCodeRepository.findByCode("SUMMER2024")).thenReturn(promoCode);
        DiscountResult discountResult = discountResultService.discountProduct(1L, "SUMMER2024");
        assertEquals(discountResult.getWarning(), "Currency mismatch");
    }


    @Test
    void shouldReturnWarningForPromoCodeUsageLimit() {
        Product product = new Product(1L,
                "Apple", BigDecimal.valueOf(55.55),
                "PLN",
                null);
        final PromoCode promoCode = new PromoCode(1L,
                "SUMMER2024",
                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                "PLN",
                5,
                5);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(promoCodeRepository.findByCode("SUMMER2024")).thenReturn(promoCode);
        DiscountResult discountResult = discountResultService.discountProduct(1L, "SUMMER2024");
        assertEquals(discountResult.getWarning(), "Promo code usage limit reached");
    }

    @Test
    void shouldReturnPriceEqualsZeroBecauseDiscountPriceIsBelowZero() {
        Product product = new Product(1L,
                "Apple", BigDecimal.valueOf(14.14),
                "PLN",
                null);
        final PromoCode promoCode = new PromoCode(1L,
                "SUMMER2024",
                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.now(),
                BigDecimal.valueOf(25.55),
                "PLN",
                5,
                0);


        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(promoCodeRepository.findByCode("SUMMER2024")).thenReturn(promoCode);
        DiscountResult discountResult = discountResultService.discountProduct(1L, "SUMMER2024");
        assertEquals(discountResult.getResultPrice().setScale(2), BigDecimal.valueOf(0).setScale(2));
    }


}
