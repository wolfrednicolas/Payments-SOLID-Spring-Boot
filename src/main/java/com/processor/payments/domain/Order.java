package com.processor.payments.domain;

public class Order {
    private final String id = java.util.UUID.randomUUID().toString();
    private final Money total;

    public Order(Money total) { this.total = total; }
    public String id() { return id; }
    public Money total() { return total; }
}
