package com.sii.promoCodes.Controllers;


import com.sii.promoCodes.Models.DiscountResult;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Services.DiscountResultService;
import com.sii.promoCodes.Services.PromoCodeService;
import com.sii.promoCodes.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;


@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PromoCodeService promoCodeService;

    private final PurchaseService purchaseService;

    private final DiscountResultService discountResultService;

    public PurchaseController(PromoCodeService promoCodeService, PurchaseService purchaseService, DiscountResultService discountResultService) {
        this.promoCodeService = promoCodeService;
        this.purchaseService = purchaseService;
        this.discountResultService = discountResultService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {

        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase simulatePurchaseWithoutPromoCode(@PathVariable long id){
            return discountResultService.simulatePurchaseWithoutPromoCode(id);
    }

    @PostMapping("/{id}/{promoCodeStr}")
    public ResponseEntity<Purchase> simulatePurchaseWithPromoCode(@PathVariable long id, @PathVariable String promoCodeStr) {
        PromoCode promoCode = promoCodeService.getPromoCode(promoCodeStr);
        if (promoCode == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Purchase purchase =  discountResultService.simulatePurchaseWithPromoCode(id, promoCodeStr);
        return ResponseEntity.ok(purchase);
    }
}
