package com.sii.promoCodes.Repositories;

import com.sii.promoCodes.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
