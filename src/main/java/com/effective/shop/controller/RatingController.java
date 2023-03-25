package com.effective.shop.controller;

import com.effective.shop.entity.Product;
import com.effective.shop.entity.Rating;
import com.effective.shop.model.dto.RatingDTO;
import com.effective.shop.sevice.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public Rating addRating(@Valid @RequestBody RatingDTO ratingDTO) {
        Rating rating = Rating.builder()
                .product(Product.builder().id(ratingDTO.getProductId()).build())
                .rating(ratingDTO.getRating())
                .review(ratingDTO.getReview())
                .build();
        return ratingService.add(rating);
    }
}
