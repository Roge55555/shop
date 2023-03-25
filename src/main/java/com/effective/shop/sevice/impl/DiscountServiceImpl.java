package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Discount;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.repository.DiscountRepository;
import com.effective.shop.sevice.DiscountService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    private final UserService userService;

    @Override
    public Discount add(Discount discount) {
        discount.setCreator(userService.findByLogin(Utils.getLogin()));

        return discountRepository.save(discount);
    }

    @Override
    public Discount findById(Long id) {
        return discountRepository.findById(id).orElseThrow(() -> {
        throw new NoSuchElementException(id);
        });
    }

    @Override
    public Discount update(Discount discount) {
        Discount updatedDiscount = findById(discount.getId());

        if (Objects.nonNull(discount.getName())) {
            updatedDiscount.setName(discount.getName());
        }
        if (Objects.nonNull(discount.getAmount())) {
            updatedDiscount.setAmount(discount.getAmount());
        }
        if (Objects.nonNull(discount.getDiscountFrom())) {
            updatedDiscount.setDiscountFrom(discount.getDiscountFrom());
        }
        if (Objects.nonNull(discount.getDiscountTo())) {
            updatedDiscount.setDiscountTo(discount.getDiscountTo());
        }

        return discountRepository.save(updatedDiscount);
    }
}
