package com.sii.promoCodes.Controllers;


import com.sii.promoCodes.Models.PromoCode;
//import com.sii.promoCodes.Services.PromoCodeService;
import com.sii.promoCodes.Services.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocodes")
public class PromoCodeController {
    @Autowired
    private PromoCodeService promoCodeService;

    @PostMapping
    public ResponseEntity<PromoCode> createPromoCode(@RequestBody PromoCode promoCode) {
        try{
            if (promoCode.getCode().length()>=3 && promoCode.getCode().length()<=24 ){
                PromoCode createdPromoCode = promoCodeService.createPromoCode(promoCode);
                return ResponseEntity.ok(createdPromoCode);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<PromoCode>> getAllPromoCodes() {

        List<PromoCode> promoCodes = promoCodeService.getAllPromoCodes();
        return ResponseEntity.ok(promoCodes);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PromoCode> getPromoCodeByCode(@PathVariable String code) {
        PromoCode promoCode = promoCodeService.getPromoCodeByCode(code);

        if (promoCode == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(promoCode);
    }
}
