package com.sii.promoCodes.Services;

import com.sii.promoCodes.Models.DiscountResult;
import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.ProductRepository;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DiscountResultService {
    private final PromoCodeService promoCodeService;
    private final ProductService productService;
    private final PromoCodeRepository promoCodeRepository;
    private final PurchaseRepository purchaseRepository;

    public DiscountResultService(PromoCodeService promoCodeService, ProductService productService, PromoCodeRepository promoCodeRepository, PurchaseRepository purchaseRepository) {
        this.promoCodeService = promoCodeService;
        this.productService = productService;
        this.promoCodeRepository = promoCodeRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public DiscountResult discountProduct(long id, String promoCodeStr) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            DiscountResult discountResult = new DiscountResult(product.getName(), product.getPrice(),product.getCurrency(), null);
            PromoCode promoCode = promoCodeRepository.findByCode(promoCodeStr);
            BigDecimal discountAmount = BigDecimal.ZERO;

            if (promoCode.getExpirationDate().isBefore(LocalDateTime.now())) {
                discountResult.setWarning("Promo code expired");

            }

            else if (!promoCode.getCurrency().equals(product.getCurrency())) {
                discountResult.setWarning("Currency mismatch");
            }

            else if (promoCode.getCurrentUsages() >= promoCode.getMaxUsages()) {
                discountResult.setWarning("Promo code usage limit reached");
            }

            else{

                discountAmount = promoCode.getDiscountAmount();
                BigDecimal finalPrice = product.getPrice().subtract(discountAmount);

                if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
                    finalPrice = BigDecimal.ZERO;
                }
                discountResult.setResultPrice(finalPrice);


//                promoCode.setCurrentUsages(promoCode.getCurrentUsages() + 1);
//                promoCodeRepository.save(promoCode);
            }

            return discountResult;
        }else {
            throw new RuntimeException("Product not found");

        }

    }


    public Purchase simulatePurchaseWithPromoCode(long id, String promoCodeStr) {
        String warning = null;
        Optional<Product> productOptional = productService.getProductById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            PromoCode promoCode = promoCodeService.getPromoCode(promoCodeStr);
            BigDecimal regularPrice = product.getPrice();
            Purchase purchase = new Purchase(LocalDateTime.now(),regularPrice,promoCode.getDiscountAmount(), regularPrice, null);
                if (promoCode.getExpirationDate().isBefore(LocalDateTime.now())) {
                    warning = "Promo code expired";
                }
                else if (!promoCode.getCurrency().equals(product.getCurrency())) {
                    warning = "Currency mismatch";

                }
                else if (promoCode.getCurrentUsages() >= promoCode.getMaxUsages()) {
                    warning = "Promo code usage limit reached";
                }
                else {

                    BigDecimal discountAmount = promoCode.getDiscountAmount();
                    promoCode.setCurrentUsages(promoCode.getCurrentUsages() + 1);
                    promoCodeRepository.save(promoCode);
                    BigDecimal finalPrice = regularPrice.subtract(discountAmount).max(BigDecimal.ZERO);
                    purchase.setFinalPrice(finalPrice);

                }
            purchase.setWarning(warning);
            purchaseRepository.save(purchase);

            return purchase;

        }
        else {
            throw new RuntimeException("Product not found");
        }

    }

    public Purchase simulatePurchaseWithoutPromoCode(long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            BigDecimal regularPrice = product.getPrice();
            Purchase purchase = new Purchase(LocalDateTime.now(),regularPrice,BigDecimal.valueOf(0), regularPrice, null);
            return purchase;
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
