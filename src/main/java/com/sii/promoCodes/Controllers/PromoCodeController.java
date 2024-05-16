package com.sii.promoCodes.Controllers;

import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromoCodeController {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @GetMapping("/promocodes")
    public String promocodesMain(Model model) {
        Iterable<PromoCode> promoCodes = promoCodeRepository.findAll();
        model.addAttribute("promoCodes", promoCodes);
        return "promocodes";
    }
}
