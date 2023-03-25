package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.CharacteristicTable;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.exceptions.TooLowAccessException;
import com.effective.shop.model.Role;
import com.effective.shop.repository.CharacteristicTableRepository;
import com.effective.shop.sevice.CharacteristicService;
import com.effective.shop.sevice.CharacteristicTableService;
import com.effective.shop.sevice.ProductService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CharacteristicTableServiceImpl implements CharacteristicTableService {

    private final CharacteristicTableRepository characteristicTableRepository;

    private final CharacteristicService characteristicService;

    private final ProductService productService;

    private final UserService userService;

    @Override
    public CharacteristicTable add(CharacteristicTable characteristicTable) {
        if(productService.findById(characteristicTable.getProduct().getId()).getOrganization().getCreator().getLogin().equals(Utils.getLogin()) ||
                userService.findByLogin(Utils.getLogin()).getRole().getName().equals(Role.ADMIN)) {
            characteristicTable.setCharacteristic(characteristicService.findById(characteristicTable.getCharacteristic().getId()));
            characteristicTable.setProduct(productService.findById(characteristicTable.getProduct().getId()));
            return characteristicTableRepository.save(characteristicTable);
        }
        else {
            throw new TooLowAccessException("Only product owner and admin can add characteristics!");
        }
    }

    @Override
    public CharacteristicTable update(CharacteristicTable characteristicTable) {
        if(productService.findById(characteristicTable.getProduct().getId()).getOrganization().getCreator().getLogin().equals(Utils.getLogin()) ||
                userService.findByLogin(Utils.getLogin()).getRole().getName().equals(Role.ADMIN)) {

            CharacteristicTable updatedTable = findById(characteristicTable.getId());

            if (Objects.nonNull(characteristicTable.getProduct().getId())) {
                updatedTable.setProduct(productService.findById(characteristicTable.getProduct().getId()));
            }
            if (Objects.nonNull(characteristicTable.getCharacteristic().getId())) {
                updatedTable.setCharacteristic(characteristicService.findById(characteristicTable.getCharacteristic().getId()));
            }
            if (Objects.nonNull(characteristicTable.getValue())) {
                updatedTable.setValue(characteristicTable.getValue());
            }

            return characteristicTableRepository.save(characteristicTable);
        }
        else {
            throw new TooLowAccessException("Only product owner and admin can add characteristics!");
        }
    }

    @Override
    public CharacteristicTable findById(Long id) {
        return characteristicTableRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException(id);
        });
    }
}
