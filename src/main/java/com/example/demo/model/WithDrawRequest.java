package com.example.demo.model;

import java.math.BigDecimal;

public class WithDrawRequest {
    private String accountNumber;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
