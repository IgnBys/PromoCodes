package com.sii.promoCodes.repository;

import com.sii.promoCodes.Models.PromoCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PromoCodeRepository extends CrudRepository<PromoCode, String> {
}
