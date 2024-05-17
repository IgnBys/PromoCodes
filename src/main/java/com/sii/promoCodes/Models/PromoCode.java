package com.sii.promoCodes.Models;

import jakarta.ejb.Local;
import jakarta.persistence.*;

import java.math.BigDecimal;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Entity
public class PromoCode{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expirationDate;



    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private BigDecimal discountAmount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private int maxUsages;

    @Column(nullable = false)
    private int currentUsages;


    public PromoCode() {

    }
    public PromoCode(Long id, String code, LocalDateTime expirationDate, LocalDateTime startedAt, BigDecimal discountAmount, String currency, int maxUsages, int currentUsages) {
        this.id = id;
        this.code = code;
        this.expirationDate = expirationDate;
        this.startedAt = startedAt;
        this.discountAmount = discountAmount;
        this.currency = currency;
        this.maxUsages = maxUsages;
        this.currentUsages = currentUsages;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public LocalDateTime getStartedAt() {
        return startedAt.truncatedTo(ChronoUnit.SECONDS);
//        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMaxUsages() {
        return maxUsages;
    }

    public void setMaxUsages(int maxUsages) {
        this.maxUsages = maxUsages;
    }

    public int getCurrentUsages() {
        return currentUsages;
    }

    public void setCurrentUsages(int currentUsages) {
        this.currentUsages = currentUsages;
    }



}
