package com.sii.promoCodes.Controllers;



import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/{productId}/{promoCode}")
    public ResponseEntity<Purchase> simulatePurchase(@PathVariable Long productId, @PathVariable String promoCode) {
        try {
            Purchase purchase = purchaseService.simulatePurchase(productId, promoCode);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
