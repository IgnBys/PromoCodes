package com.sii.promoCodes.Services;

import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.ProductRepository;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public Purchase simulatePurchase(Long productId, String promoCodeStr) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        PromoCode promoCode = promoCodeRepository.findByCode(promoCodeStr);

        if (promoCode == null) {
            throw new RuntimeException("Promo code not found");
        }

        BigDecimal discountAmount = BigDecimal.ZERO;



        if (promoCode.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Promo code expired");
        }

        if (!promoCode.getCurrency().equals(product.getCurrency())) {
            throw new RuntimeException("Currency mismatch");
        }

        if (promoCode.getCurrentUsages() >= promoCode.getMaxUsages()) {
            throw new RuntimeException("Promo code usage limit reached");
        }

        discountAmount = promoCode.getDiscountAmount();
        BigDecimal finalPrice = product.getPrice().subtract(discountAmount);

        if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
            finalPrice = BigDecimal.ZERO;
        }

        Purchase purchase = new Purchase();
//        purchase.setProduct(product);
//        purchase.setPromoCode(promoCode);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setRegularPrice(product.getPrice());
        purchase.setDiscountAmount(discountAmount);
        purchase.setFinalPrice(finalPrice);

        promoCode.setCurrentUsages(promoCode.getCurrentUsages() + 1);
        promoCodeRepository.save(promoCode);

        return purchaseRepository.save(purchase);
    }
}