package com.processor.payments.dto;

public record PaymentResponse(
        String orderId,
        String transactionId,
        String method
) {}
