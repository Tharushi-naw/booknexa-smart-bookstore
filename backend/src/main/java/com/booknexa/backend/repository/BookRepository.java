package com.booknexa.backend.repository;

import com.booknexa.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String title,
            String author,
            String category
    );

    List<Book> findByCategoryIgnoreCaseAndQuantityGreaterThan(String category, Integer quantity);

    List<Book> findByQuantityGreaterThan(Integer quantity);
}