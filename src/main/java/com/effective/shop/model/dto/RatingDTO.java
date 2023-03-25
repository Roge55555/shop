package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDTO {

    @PositiveOrZero
    private Double rating;

    private String review;

    private Long productId;
}
