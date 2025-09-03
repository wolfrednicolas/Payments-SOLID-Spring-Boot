package com.processor.payments.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OrderServiceIT {

    @Autowired
    private OrderService service;

    @Test
    void charge_card_ok() {
        var result = service.charge("card", new BigDecimal("50.00"), "USD");
        assertThat(result.transactionId()).startsWith("CARD-");
        assertThat(result.method()).isEqualTo("card");
    }

    @Test
    void refund_unsupported_for_crypto() {
        assertThrows(UnsupportedOperationException.class,
                () -> service.refund("crypto", "CRYPTO-123"));
    }
}
