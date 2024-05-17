package com.sii.promoCodes.Services;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PromoCodeService {
    private PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode createPromoCode(PromoCode promoCode) {
        promoCode.setCurrentUsages(0);
        return promoCodeRepository.save(promoCode);
    }

    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

    public Optional<PromoCode> getPromoCodeById(Long id) {
        return promoCodeRepository.findById(id);
    }

    public PromoCode getPromoCodeByCode(String code) {
        return promoCodeRepository.findByCode(code);
    }
}
