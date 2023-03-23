package com.effective.shop.sevice;

import com.effective.shop.model.dto.AuthenticationRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationRestService {

    ResponseEntity<?> login(AuthenticationRequestDTO requestDTO);
}
