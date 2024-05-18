package com.sii.promoCodes.Models;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private PromoCode promoCode;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private BigDecimal regularPrice;

    @Column(nullable = false)
    private BigDecimal discountAmount;

    private BigDecimal finalPrice;

    public Purchase(){

    }

    public Purchase(Long id, LocalDateTime purchaseDate, BigDecimal regularPrice, BigDecimal discountAmount, BigDecimal finalPrice) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.regularPrice = regularPrice;
        this.discountAmount = discountAmount;
        this.finalPrice = finalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
//
//    public PromoCode getPromoCode() {
//        return promoCode;
//    }
//
//    public void setPromoCode(PromoCode promoCode) {
//        this.promoCode = promoCode;
//    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}