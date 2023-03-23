package com.effective.shop.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TooLowAccessException extends RuntimeException {

    private String message;

    public TooLowAccessException(String message) {
        this.message = message;
    }

}
