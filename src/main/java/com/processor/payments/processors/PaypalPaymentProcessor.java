package com.processor.payments.processors;

import com.processor.payments.domain.Order;
import com.processor.payments.ports.PaymentProcessor;
import com.processor.payments.ports.Refundable;
import org.springframework.stereotype.Component;

@Component("paypal")
public class PaypalPaymentProcessor implements PaymentProcessor, Refundable {
    @Override
    public String charge(Order order) {
        return "PAYPAL-" + order.id();
    }
    @Override
    public void refund(String transactionId) {
        // Simulate refund
    }
}
