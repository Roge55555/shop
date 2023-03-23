package com.effective.shop.controller;

import com.effective.shop.model.dto.AuthenticationRequestDTO;
import com.effective.shop.sevice.AuthenticationRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationRestController {

    private final AuthenticationRestService authenticationRestService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO requestDTO) {
        return authenticationRestService.login(requestDTO);
    }

}
