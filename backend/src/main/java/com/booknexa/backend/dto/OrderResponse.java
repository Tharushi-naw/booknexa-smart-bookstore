package com.booknexa.backend.dto;

import com.booknexa.backend.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private Long orderId;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItemResponse> items;

    public OrderResponse(Long orderId, String customerName, String customerEmail,
                         BigDecimal totalAmount, BigDecimal discountAmount,
                         BigDecimal finalAmount, LocalDateTime orderDate,
                         OrderStatus status, List<OrderItemResponse> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}