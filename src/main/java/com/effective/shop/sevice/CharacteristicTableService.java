package com.effective.shop.sevice;

import com.effective.shop.entity.CharacteristicTable;

public interface CharacteristicTableService {

    CharacteristicTable add(CharacteristicTable characteristicTable);

    CharacteristicTable update(CharacteristicTable characteristicTable);

    CharacteristicTable findById(Long id);
}
