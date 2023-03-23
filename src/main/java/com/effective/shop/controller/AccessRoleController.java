package com.effective.shop.controller;

import com.effective.shop.entity.AccessRole;
import com.effective.shop.sevice.AccessRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accessRoles")
public class AccessRoleController {

    private final AccessRoleService accessRoleService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AccessRole getById(@PathVariable("id") Long id) {
        return accessRoleService.findById(id);
    }
}
