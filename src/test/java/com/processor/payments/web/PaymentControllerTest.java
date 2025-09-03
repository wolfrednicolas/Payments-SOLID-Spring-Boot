package com.processor.payments.web;

import com.processor.payments.app.OrderService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void charge_ok() throws Exception {
        OrderService.Result result = new OrderService.Result("ORD-1", "CARD-ORD-1", "card");
        Mockito.when(orderService.charge(Mockito.eq("card"), Mockito.any(), Mockito.eq("USD")))
                .thenReturn(result);

        String json = "{\"method\":\"card\",\"amount\":149.90,\"currency\":\"USD\"}";

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("ORD-1"))
                .andExpect(jsonPath("$.transactionId").value("CARD-ORD-1"))
                .andExpect(jsonPath("$.method").value("card"));
    }

    @Test
    void refund_noContent() throws Exception {
        Mockito.doNothing().when(orderService).refund("paypal", "PAYPAL-123");
        mockMvc.perform(post("/api/payments/paypal/refunds/PAYPAL-123"))
                .andExpect(status().isNoContent());
    }
}
