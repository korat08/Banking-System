package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "accounts")
public class Account {

    @Id
    @NotNull()
    @Pattern(regexp = "\\d{10}", message = "Account number must be exactly 10 digits")
    @Column(name = "account_number", length = 10, nullable = false,unique = true)
    private String accountNumber;

    @NotNull()
    @DecimalMin(value = "0.0",inclusive = true)
    @Digits(integer = 15,fraction = 2)
    @Column(nullable = false,precision = 17,scale = 2)
    private BigDecimal balance;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;



    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
