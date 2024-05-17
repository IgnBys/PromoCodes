package com.sii.promoCodes.Controllers;


import com.sii.promoCodes.Models.PromoCode;
//import com.sii.promoCodes.Services.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocodes")
public class PromoCodeController {
//    @Autowired
//    private PromoCodeService promoCodeService;
//
//    @PostMapping
//    public ResponseEntity<PromoCode> createPromoCode(@RequestBody PromoCode promoCode) {
//        PromoCode createdPromoCode = promoCodeService.createPromoCode(promoCode);
//        return ResponseEntity.ok(createdPromoCode);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PromoCode>> getAllPromoCodes() {
//        List<PromoCode> promoCodes = promoCodeService.getAllPromoCodes();
//        return ResponseEntity.ok(promoCodes);
//    }
//
//    @GetMapping("/{code}")
//    public ResponseEntity<PromoCode> getPromoCodeByCode(@PathVariable String code) {
//        PromoCode promoCode = promoCodeService.getPromoCodeByCode(code);
//        if (promoCode == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(promoCode);
//    }
}
