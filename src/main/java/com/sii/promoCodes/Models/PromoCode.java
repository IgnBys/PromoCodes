package com.sii.promoCodes.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Entity
public class PromoCode{

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Id
    private String code;
    private Date expirationDate;
    private double discountAmount;
    private String currency;
    private int maxUsages;


    public PromoCode() {
        this.code = generateUniqueCode();
    }
    public PromoCode(Date expirationDate, double discountAmount, String currency, int maxUsages) {
        this.code = generateUniqueCode();
        this.expirationDate = expirationDate;
        this.discountAmount = discountAmount;
        this.currency = currency;
        this.maxUsages = maxUsages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
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

//    private String generateUniqueCode() {
//        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
//    }

    private static String generateUniqueCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder(24);
        for (int i = 0; i < 24; i++) {
            codeBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return codeBuilder.toString();
    }
}
