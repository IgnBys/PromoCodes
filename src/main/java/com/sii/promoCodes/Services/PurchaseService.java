package com.sii.promoCodes.Services;

import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final ProductService productService;
    private final PromoCodeService promoCodeService;
    private final PromoCodeRepository promoCodeRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(ProductService productService, PromoCodeService promoCodeService, PromoCodeRepository promoCodeRepository, PurchaseRepository purchaseRepository) {
        this.productService = productService;
        this.promoCodeService = promoCodeService;
        this.promoCodeRepository = promoCodeRepository;
        this.purchaseRepository = purchaseRepository;
    }






    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}