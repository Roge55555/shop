package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Rating;
import com.effective.shop.exceptions.TooLowAccessException;
import com.effective.shop.model.Role;
import com.effective.shop.model.Status;
import com.effective.shop.repository.RatingRepository;
import com.effective.shop.sevice.ProductService;
import com.effective.shop.sevice.PurchaseService;
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

    private final PurchaseService purchaseService;

    @Override
    public Rating add(Rating rating) {
        if(purchaseService.findAllUserIdWhoBuyProductById(rating.getProduct().getId()).contains(userService.findByLogin(Utils.getLogin()).getId()) ||
                userService.findByLogin(Utils.getLogin()).getRole().getName().equals(Role.ADMIN)) {
            rating.setCreator(userService.findByLogin(Utils.getLogin()));
            rating.setProduct(productService.findById(rating.getProduct().getId()));
            return ratingRepository.save(rating);
        }
        else {
            throw new TooLowAccessException("Only user who bought product and admin can create review and rating");
        }
    }
}
