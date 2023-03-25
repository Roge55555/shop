package com.effective.shop.sevice.impl;

import com.effective.shop.entity.Characteristic;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.repository.CharacteristicRepository;
import com.effective.shop.sevice.CharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    @Override
    public Characteristic add(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public Characteristic findById(Long id) {
        return characteristicRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException(id);
        });
    }

    @Override
    public Characteristic findByName(String name) {
        return characteristicRepository.findByName(name).orElseThrow(() -> {
            throw new NoSuchElementException(name);
        });
    }
}
