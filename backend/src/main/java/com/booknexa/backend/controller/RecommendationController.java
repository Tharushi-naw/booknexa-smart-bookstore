package com.booknexa.backend.controller;

import com.booknexa.backend.model.Book;
import com.booknexa.backend.service.RecommendationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "http://localhost:5173")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/my")
    public List<Book> getMyRecommendations(Authentication authentication) {
        String email = authentication.getName();
        return recommendationService.getRecommendationsForUser(email);
    }

    @GetMapping("/popular")
    public List<Book> getPopularBooks() {
        return recommendationService.getPopularAvailableBooks();
    }
}