package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {

    private Long productId;

    @Positive
    private Long amount;
}
