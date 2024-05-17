package com.sii.promoCodes.Repositories;

import com.sii.promoCodes.Models.PromoCode;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    PromoCode findByCode(String code);
}


