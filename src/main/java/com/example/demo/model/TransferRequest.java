package com.example.demo.model;

import java.math.BigDecimal;

public class TransferRequest {

    private String senderAccountNumber;
    private String receiverAccountNumber;
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
