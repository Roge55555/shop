package com.effective.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeBalance {

    @Column
    @PositiveOrZero
    private Double balance;

    @Column
    private Long userId;

}
