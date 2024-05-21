package com.sii.promoCodes.Controllers;

import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Services.PromoCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promocodes")
public class PromoCodeController {
    private final PromoCodeService promoCodeService;

    public PromoCodeController(PromoCodeService promoCodeService) {
        this.promoCodeService = promoCodeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PromoCode createPromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeService.createPromoCode(promoCode);
    }


    @GetMapping
    public ResponseEntity<List<PromoCode>> getAllPromoCodes() {

        List<PromoCode> promoCodes = promoCodeService.getAllPromoCodes();
        return ResponseEntity.ok(promoCodes);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PromoCode> getPromoCodeByCode(@PathVariable String code) {
        PromoCode promoCode = promoCodeService.getPromoCode(code);

        if (promoCode == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(promoCode);
    }
}