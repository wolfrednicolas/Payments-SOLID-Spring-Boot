package com.processor.payments.web;

import com.processor.payments.app.OrderService;
import com.processor.payments.dto.PaymentRequest;
import com.processor.payments.dto.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final OrderService service;

    public PaymentController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> charge(@RequestBody @Valid PaymentRequest req) {
        var result = service.charge(req.method(), req.amount(), req.currency());
        return ResponseEntity.ok(new PaymentResponse(result.orderId(), result.transactionId(), result.method()));
    }

    @PostMapping("/{method}/refunds/{txId}")
    public ResponseEntity<Void> refund(@PathVariable String method, @PathVariable String txId) {
        service.refund(method, txId);
        return ResponseEntity.noContent().build();
    }
}
