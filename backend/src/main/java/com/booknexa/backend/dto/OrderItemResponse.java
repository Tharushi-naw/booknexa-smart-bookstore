package com.booknexa.backend.dto;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public OrderItemResponse(Long bookId, String bookTitle, Integer quantity,
                             BigDecimal unitPrice, BigDecimal subtotal) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}