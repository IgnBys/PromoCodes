package com.sii.promoCodes.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private Date expirationDate;



    @CreationTimestamp
    @Column(name =  "started_at")
    private Date startedAt;

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
    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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


}
