package com.effective.shop.controller;

import com.effective.shop.entity.Characteristic;
import com.effective.shop.sevice.CharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characteristics")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public Characteristic addCharacteristic(@PathVariable String name) {
        return characteristicService.add(Characteristic.builder().name(name).build());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public Characteristic getById(@PathVariable("id") Long id) {
        return characteristicService.findById(id);
    }
}
