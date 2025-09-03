package com.processor.payments.ports;

public interface Refundable {
    void refund(String transactionId);
}
