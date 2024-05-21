package com.sii.promoCodes.Services;

import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}