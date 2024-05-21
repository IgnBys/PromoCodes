package com.sii.promoCodes.Models;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountResult {
    private String name;

    private BigDecimal resultPrice;
    private String Currency;


    private String warning;


    public DiscountResult(){

    }
    public DiscountResult(String name, BigDecimal resultPrice, String currency, String warning) {
        this.name = name;
        this.resultPrice = resultPrice;
        Currency = currency;
        this.warning = warning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public BigDecimal getResultPrice() {
        return resultPrice;
    }

    public void setResultPrice(BigDecimal resultPrice) {
        this.resultPrice = resultPrice;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
