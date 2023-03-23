package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAddDTO {

    @NotBlank
    @Size(min = 4)
    private String login;

    @Size(min = 5)
    private String password;

    @NotBlank
    @Email
    private String email;

}