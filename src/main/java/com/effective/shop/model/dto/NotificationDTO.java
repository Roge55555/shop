package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDTO {

    private Long receiverId;

    @Size(min = 3)
    @NotBlank
    private String title;

    private String message;
}
