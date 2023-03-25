package com.effective.shop.sevice;

import com.effective.shop.entity.Characteristic;

public interface CharacteristicService {

    Characteristic add(Characteristic characteristic);

    Characteristic findById(Long id);

    Characteristic findByName(String name);
}
