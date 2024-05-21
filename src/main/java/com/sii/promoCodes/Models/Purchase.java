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
    @Column(nullable = false)
    private String warning;
    @Column(nullable = false)
    private LocalDateTime purchaseDate;
    @Column(nullable = false)
    private BigDecimal regularPrice;
    @Column(nullable = false)
    private BigDecimal discountAmount;
    @Column(nullable = false)
    private BigDecimal finalPrice;

    public Purchase(){

    }

    public Purchase(Long id, Product product, LocalDateTime purchaseDate, BigDecimal regularPrice, BigDecimal discountAmount, BigDecimal finalPrice, String warning) {
        this.id = id;
        this.product = product;
        this.purchaseDate = purchaseDate;
        this.regularPrice = regularPrice;
        this.discountAmount = discountAmount;
        this.finalPrice = finalPrice;
        this.warning = warning;

    }

    public Purchase(Product product, LocalDateTime purchaseDate, BigDecimal regularPrice, BigDecimal discountAmount, BigDecimal finalPrice, String warning) {
        this.product = product;
        this.purchaseDate = purchaseDate;
        this.regularPrice = regularPrice;
        this.discountAmount = discountAmount;
        this.finalPrice = finalPrice;
        this.warning = warning;
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

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}