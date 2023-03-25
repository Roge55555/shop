package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Rating;
import com.effective.shop.repository.RatingRepository;
import com.effective.shop.sevice.ProductService;
import com.effective.shop.sevice.RatingService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final UserService userService;

    private final ProductService productService;

    @Override
    public Rating add(Rating rating) {
        rating.setCreator(userService.findByLogin(Utils.getLogin()));
        rating.setProduct(productService.findById(rating.getProduct().getId()));
        return ratingRepository.save(rating);
    }
}
