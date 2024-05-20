package com.sii.promoCodes.Services;
import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Repositories.ProductRepository;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final PromoCodeRepository promoCodeRepository;

    public ProductService(ProductRepository productRepository, PromoCodeRepository promoCodeRepository) {
        this.productRepository = productRepository;
        this.promoCodeRepository = promoCodeRepository;
    }

    public Product discountProduct(String productName, String promoCodeStr) {

        Product productOptional = getProductByName(productName);
        if(productOptional != null) {
            Product product = productOptional;
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
        }else {
            throw new RuntimeException("Product not found");

        }

    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}