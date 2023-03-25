package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacteristicTableDTO {

    private Long productId;

    private Long characteristicId;

    String value;
}
