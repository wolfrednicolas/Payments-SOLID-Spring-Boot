package com.processor.payments.processors;

import com.processor.payments.domain.Order;
import com.processor.payments.ports.PaymentProcessor;
import org.springframework.stereotype.Component;

@Component("crypto")
public class CryptoPaymentProcessor implements PaymentProcessor {
    @Override
    public String charge(Order order) {
        return "CRYPTO-" + order.id();
    }
}
