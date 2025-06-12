package com.example.demo.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public class TransferRequest {

    @NotNull(message = "Sender account number must not be null")
    @Pattern(regexp = "\\d{10}", message = "Account number must be exactly 10 digits")
    private String senderAccountNumber;

    @NotNull(message = "Receiver account number must not be null")
    @Pattern(regexp = "\\d{10}", message = "Account number must be exactly 10 digits")
    private String receiverAccountNumber;

    @NotNull
    @DecimalMin(value = "1", message = "Transfer amount must be greater than zero")
    private BigDecimal amount;

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
