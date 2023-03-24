package com.effective.shop.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@Getter
@ResponseStatus(code = HttpStatus.CONFLICT)
public class NotEnoughElementException extends RuntimeException {

    private String message;

    public NotEnoughElementException(String message) {
        this.message = message;
    }
}
