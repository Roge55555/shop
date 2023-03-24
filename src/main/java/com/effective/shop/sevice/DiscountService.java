package com.effective.shop.sevice;

import com.effective.shop.entity.Discount;

public interface DiscountService {

    Discount add(Discount discount);

    Discount findById(Long id);

    Discount update(Discount discount);
}
