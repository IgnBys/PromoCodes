package com.sii.promoCodes.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private BigDecimal discountAmount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private int maxUsages;

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

    @Column(nullable = false)
    private int currentUsages;

//
//    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//
//    private String code;
//    private Date expirationDate;
//    private double discountAmount;
//    private String currency;
//    private int maxUsages;
//
//
//    public PromoCode() {
//        this.code = generateUniqueCode();
//    }
//    public PromoCode(Date expirationDate, double discountAmount, String currency, int maxUsages) {
//        this.code = generateUniqueCode();
//        this.expirationDate = expirationDate;
//        this.discountAmount = discountAmount;
//        this.currency = currency;
//        this.maxUsages = maxUsages;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public Date getExpirationDate() {
//        return expirationDate;
//    }
//
//    public void setExpirationDate(Date expirationDate) {
//        this.expirationDate = expirationDate;
//    }
//
//    public double getDiscountAmount() {
//        return discountAmount;
//    }
//
//    public void setDiscountAmount(double discountAmount) {
//        this.discountAmount = discountAmount;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public int getMaxUsages() {
//        return maxUsages;
//    }
//
//    public void setMaxUsages(int maxUsages) {
//        this.maxUsages = maxUsages;
//    }
//
////    private String generateUniqueCode() {
////        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
////    }
//
//    private static String generateUniqueCode() {
//        Random random = new Random();
//        StringBuilder codeBuilder = new StringBuilder(24);
//        for (int i = 0; i < 24; i++) {
//            codeBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
//        }
//        return codeBuilder.toString();
//    }
}
