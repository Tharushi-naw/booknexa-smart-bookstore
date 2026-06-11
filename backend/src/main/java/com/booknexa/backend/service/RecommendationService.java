package com.booknexa.backend.service;

import com.booknexa.backend.model.Book;
import com.booknexa.backend.model.Order;
import com.booknexa.backend.model.OrderItem;
import com.booknexa.backend.repository.BookRepository;
import com.booknexa.backend.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public RecommendationService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> getRecommendationsForUser(String email) {
        List<Order> userOrders = orderRepository.findByUserEmail(email);

        if (userOrders.isEmpty()) {
            return getPopularAvailableBooks();
        }

        Map<String, Integer> categoryCount = new HashMap<>();
        Set<Long> purchasedBookIds = new HashSet<>();

        for (Order order : userOrders) {
            for (OrderItem item : order.getItems()) {
                Book book = item.getBook();

                if (book != null) {
                    purchasedBookIds.add(book.getId());

                    String category = book.getCategory();
                    categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
                }
            }
        }

        if (categoryCount.isEmpty()) {
            return getPopularAvailableBooks();
        }

        String favoriteCategory = categoryCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (favoriteCategory == null) {
            return getPopularAvailableBooks();
        }

        List<Book> recommendedBooks = bookRepository
                .findByCategoryIgnoreCaseAndQuantityGreaterThan(favoriteCategory, 0)
                .stream()
                .filter(book -> !purchasedBookIds.contains(book.getId()))
                .limit(10)
                .collect(Collectors.toList());

        if (recommendedBooks.isEmpty()) {
            return getPopularAvailableBooks();
        }

        return recommendedBooks;
    }

    public List<Book> getPopularAvailableBooks() {
        return bookRepository.findByQuantityGreaterThan(0)
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}