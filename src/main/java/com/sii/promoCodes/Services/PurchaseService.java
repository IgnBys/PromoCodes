package com.sii.promoCodes.Services;

import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Repositories.ProductRepository;
import com.sii.promoCodes.Repositories.PromoCodeRepository;
import com.sii.promoCodes.Repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PurchaseService {

}