package com.processor.payments.app;

import com.processor.payments.domain.Money;
import com.processor.payments.domain.Order;
import com.processor.payments.ports.PaymentProcessor;
import com.processor.payments.ports.Refundable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class OrderService {

    private final Map<String, PaymentProcessor> processors;

    public OrderService(Map<String, PaymentProcessor> processors) {
        this.processors = processors;
    }

    public Result charge(String method, BigDecimal amount, String currency) {
        PaymentProcessor processor = processors.get(method);
        if (processor == null) {
            throw new IllegalArgumentException("Unknown payment method: " + method);
        }
        Order order = new Order(new Money(amount, currency));
        String txId = processor.charge(order);
        return new Result(order.id(), txId, method);
    }

    public void refund(String method, String transactionId) {
        PaymentProcessor processor = processors.get(method);
        if (processor instanceof Refundable r) {
            r.refund(transactionId);
        } else {
            throw new UnsupportedOperationException("Method '" + method + "' does not support refunds");
        }
    }

    public record Result(String orderId, String transactionId, String method) {}
}
