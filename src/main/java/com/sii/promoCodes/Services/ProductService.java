package com.sii.promoCodes.Services;

import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.ProductRepository;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public Product discountProduct(String productName, String promoCodeStr) {


        Product product = productRepository.findByName(productName).orElseThrow(() -> new RuntimeException("Product not found"));
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
        product.setPrice(finalPrice);


        promoCode.setCurrentUsages(promoCode.getCurrentUsages() + 1);
        promoCodeRepository.save(promoCode);

        return productRepository.save(product);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
