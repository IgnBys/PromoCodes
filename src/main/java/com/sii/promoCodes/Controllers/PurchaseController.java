package com.sii.promoCodes.Controllers;


import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Services.PromoCodeService;
import com.sii.promoCodes.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private final PromoCodeService promoCodeService;

    @Autowired
    private final PurchaseService purchaseService;

    public PurchaseController(PromoCodeService promoCodeService, PurchaseService purchaseService) {
        this.promoCodeService = promoCodeService;
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {

        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @PostMapping("/{productName}")
    public ResponseEntity<Purchase> simulatePurchaseWithoutPromoCode(@PathVariable String productName){
        try {
            Purchase purchase = purchaseService.simulatePurchaseWithoutPromoCode(productName);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{productName}/{promoCodeStr}")
    public ResponseEntity<Purchase> simulatePurchaseWithPromoCode(@PathVariable String productName, @PathVariable String promoCodeStr) {
        PromoCode promoCode = promoCodeService.getPromoCodeByCode(promoCodeStr);
        if (promoCode == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        try {
            Purchase purchase = purchaseService.simulatePurchaseWithPromoCode(productName, promoCodeStr);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
