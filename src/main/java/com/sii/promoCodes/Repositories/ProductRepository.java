package com.sii.promoCodes.Repositories;

import com.sii.promoCodes.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
