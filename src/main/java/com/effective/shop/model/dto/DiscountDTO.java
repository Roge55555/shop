package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountDTO {

    private String name;

    @NotNull
    @Positive
    @Max(100)
    private Long amount;

    private LocalDateTime discountFrom;

    private LocalDateTime discountTo;
}
