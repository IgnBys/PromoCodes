package com.sii.promoCodes.Services;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromoCodeService {
    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode createPromoCode(PromoCode promoCode) {
        if (!(promoCode.getCode().length() >= 3 && promoCode.getCode().length() <= 24)) {
            throw new RuntimeException("Incorrect length");
        }
        else {
            promoCode.setCurrentUsages(0);
        }
        return promoCodeRepository.save(promoCode);
    }

    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

    public PromoCode getPromoCode(String code) {
        return promoCodeRepository.findByCode(code);
    }
}