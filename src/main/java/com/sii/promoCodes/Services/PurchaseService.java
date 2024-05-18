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
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private ProductService productService;
    @Autowired
    private PromoCodeService promoCodeService;
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    public PurchaseService(ProductService productService, PromoCodeService promoCodeService, PurchaseRepository purchaseRepository) {
        this.productService = productService;
        this.promoCodeService = promoCodeService;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase simulatePurchaseWithPromoCode(String productName, String promoCodeStr) {
        Optional<Product> productOptional = productService.getProductByName(productName);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            PromoCode promoCode = promoCodeService.getPromoCodeByCode(promoCodeStr);
            BigDecimal regularPrice = product.getPrice();
            BigDecimal discountAmount = BigDecimal.ZERO;
            if (promoCode!=null) {
                if(promoCode.getExpirationDate().isBefore(LocalDateTime.now())){
                    throw new RuntimeException("Promo code expired");
                }
                if (!promoCode.getCurrency().equals(product.getCurrency())) {
                    throw new RuntimeException("Currency mismatch");
                }
                if (promoCode.getCurrentUsages() >= promoCode.getMaxUsages()) {
                    throw new RuntimeException("Promo code usage limit reached");
                }
                discountAmount = promoCode.getDiscountAmount();
                promoCode.setCurrentUsages(promoCode.getCurrentUsages() + 1);
                promoCodeRepository.save(promoCode);

            }
            BigDecimal finalPrice = regularPrice.subtract(discountAmount).max(BigDecimal.ZERO);

            Purchase purchase = new Purchase();
            purchase.setPurchaseDate(LocalDateTime.now());
            purchase.setRegularPrice(regularPrice);
            purchase.setDiscountAmount(discountAmount);
            purchase.setProduct(product);
            purchase.setFinalPrice(finalPrice);

            return purchaseRepository.save(purchase);
        }
        else {
            throw new RuntimeException("Product not found");
        }

    }

    public Purchase simulatePurchaseWithoutPromoCode(String productName) {
        Optional<Product> productOptional = productService.getProductByName(productName);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            BigDecimal regularPrice = product.getPrice();
            BigDecimal discountAmount = BigDecimal.ZERO;

            BigDecimal finalPrice = regularPrice.subtract(discountAmount).max(BigDecimal.ZERO);

            Purchase purchase = new Purchase();
            purchase.setPurchaseDate(LocalDateTime.now());
            purchase.setRegularPrice(regularPrice);
            purchase.setDiscountAmount(discountAmount);
            purchase.setProduct(product);
            purchase.setFinalPrice(finalPrice);

            return purchaseRepository.save(purchase);
        } else {
            throw new RuntimeException("Product not found");
        }
    }


    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}