package com.booknexa.backend.service;

import com.booknexa.backend.dto.CreateOrderRequest;
import com.booknexa.backend.dto.OrderItemRequest;
import com.booknexa.backend.dto.OrderItemResponse;
import com.booknexa.backend.dto.OrderResponse;
import com.booknexa.backend.model.Book;
import com.booknexa.backend.model.Order;
import com.booknexa.backend.model.OrderItem;
import com.booknexa.backend.model.OrderStatus;
import com.booknexa.backend.model.User;
import com.booknexa.backend.repository.BookRepository;
import com.booknexa.backend.repository.OrderRepository;
import com.booknexa.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        BookRepository bookRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderResponse placeOrder(String email, CreateOrderRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CONFIRMED);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Book book = bookRepository.findById(itemRequest.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found with id: " + itemRequest.getBookId()));

            int requestedQuantity = itemRequest.getQuantity();

            if (book.getQuantity() < requestedQuantity) {
                throw new RuntimeException("Not enough stock for book: " + book.getTitle());
            }

            BigDecimal unitPrice = book.getPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(requestedQuantity));

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(requestedQuantity);
            orderItem.setUnitPrice(unitPrice);
            orderItem.setSubtotal(subtotal);

            order.addItem(orderItem);

            book.setQuantity(book.getQuantity() - requestedQuantity);
            bookRepository.save(book);

            totalAmount = totalAmount.add(subtotal);
        }

        BigDecimal discountAmount = BigDecimal.ZERO;

        if (user.isPremium() && user.getDiscountRate() != null) {
            discountAmount = totalAmount
                    .multiply(user.getDiscountRate())
                    .setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal finalAmount = totalAmount.subtract(discountAmount);

        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discountAmount);
        order.setFinalAmount(finalAmount);

        Order savedOrder = orderRepository.save(order);

        return convertToResponse(savedOrder);
    }

    public List<OrderResponse> getMyOrders(String email) {
        return orderRepository.findByUserEmail(email)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private OrderResponse convertToResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems()
                .stream()
                .map(item -> new OrderItemResponse(
                        item.getBook().getId(),
                        item.getBook().getTitle(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getSubtotal()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getUser().getName(),
                order.getUser().getEmail(),
                order.getTotalAmount(),
                order.getDiscountAmount(),
                order.getFinalAmount(),
                order.getOrderDate(),
                order.getStatus(),
                itemResponses
        );
    }
}