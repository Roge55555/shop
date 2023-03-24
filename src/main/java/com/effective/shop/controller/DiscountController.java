package com.effective.shop.controller;

import com.effective.shop.entity.Discount;
import com.effective.shop.model.dto.DiscountDTO;
import com.effective.shop.sevice.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:permission')")
    public Discount addDiscount(@Valid @RequestBody DiscountDTO discountDTO) {
        Discount discount = Discount.builder()
                .name(discountDTO.getName())
                .amount(discountDTO.getAmount())
                .discountFrom(discountDTO.getDiscountFrom())
                .discountTo(discountDTO.getDiscountTo())
                .build();
        return discountService.add(discount);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:permission')")
    public Discount updateDiscount(@PathVariable("id") Long id, @Valid @RequestBody DiscountDTO discountDTO) {
        Discount discount = Discount.builder()
                .id(id)
                .name(discountDTO.getName())
                .amount(discountDTO.getAmount())
                .discountFrom(discountDTO.getDiscountFrom())
                .discountTo(discountDTO.getDiscountTo())
                .build();
        return discountService.update(discount);
    }

}
