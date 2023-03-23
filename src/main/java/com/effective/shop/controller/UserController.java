package com.effective.shop.controller;

import com.effective.shop.entity.User;
import com.effective.shop.model.dto.UserAddDTO;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/find/{login}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getByLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }
}
