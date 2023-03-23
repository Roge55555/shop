package com.effective.shop.controller;

import com.effective.shop.entity.User;
import com.effective.shop.model.ChangeBalance;
import com.effective.shop.model.dto.UserAddDTO;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody UserAddDTO userAddDTO) {
        User user = User.builder()
                .login(userAddDTO.getLogin())
                .password(userAddDTO.getPassword())
                .email(userAddDTO.getEmail())
                .build();
        return userService.add(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public User getById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('admin:permission')")
    public void changeBalance(@Valid @RequestBody ChangeBalance changeBalance) {
        userService.changeBalance(changeBalance.getBalance(), changeBalance.getUserId());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('admin:permission')")
    public void updateAccess(@PathVariable("id") Long id, @RequestBody String role) {
        userService.changeAccess(id, role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('user:permission') || hasAuthority('admin:permission')")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
