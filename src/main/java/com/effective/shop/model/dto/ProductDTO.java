package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private String name;

    private String description;

    @Positive
    private Double price;

    @NotNull
    @PositiveOrZero
    private Long amount;

    private String tag;

    private Long organizationId;

    private Long discountId;
}
