package com.processor.payments.ports;

import com.processor.payments.domain.Order;

public interface PaymentProcessor {
    String charge(Order order);
}
