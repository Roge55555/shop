package com.effective.shop.controller;

import com.effective.shop.entity.Characteristic;
import com.effective.shop.entity.CharacteristicTable;
import com.effective.shop.entity.Product;
import com.effective.shop.model.dto.CharacteristicTableDTO;
import com.effective.shop.sevice.CharacteristicTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parameters")
public class CharacteristicTableController {

    private final CharacteristicTableService characteristicTableService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public CharacteristicTable addDiscount(@Valid @RequestBody CharacteristicTableDTO characteristicTableDTO) {
        CharacteristicTable characteristicTable = CharacteristicTable.builder()
                .product(Product.builder().id(characteristicTableDTO.getProductId()).build())
                .characteristic(Characteristic.builder().id(characteristicTableDTO.getCharacteristicId()).build())
                .value(characteristicTableDTO.getValue())
                .build();
        return characteristicTableService.add(characteristicTable);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public CharacteristicTable updateDiscount(@PathVariable("id") Long id, @Valid @RequestBody CharacteristicTableDTO characteristicTableDTO) {
        CharacteristicTable characteristicTable = CharacteristicTable.builder()
                .id(id)
                .product(Product.builder().id(characteristicTableDTO.getProductId()).build())
                .characteristic(Characteristic.builder().id(characteristicTableDTO.getCharacteristicId()).build())
                .value(characteristicTableDTO.getValue())
                .build();
        return characteristicTableService.update(characteristicTable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('admin:permission')")
    public CharacteristicTable getById(@PathVariable("id") Long id) {
        return characteristicTableService.findById(id);
    }
}
