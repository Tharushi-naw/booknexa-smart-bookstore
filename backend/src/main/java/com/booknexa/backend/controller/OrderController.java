package com.booknexa.backend.controller;

import com.booknexa.backend.dto.CreateOrderRequest;
import com.booknexa.backend.dto.OrderResponse;
import com.booknexa.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse placeOrder(Authentication authentication,
                                    @Valid @RequestBody CreateOrderRequest request) {
        String email = authentication.getName();
        return orderService.placeOrder(email, request);
    }

    @GetMapping("/my-orders")
    public List<OrderResponse> getMyOrders(Authentication authentication) {
        String email = authentication.getName();
        return orderService.getMyOrders(email);
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }
}