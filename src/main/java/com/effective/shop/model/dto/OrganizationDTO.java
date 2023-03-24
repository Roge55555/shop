package com.effective.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizationDTO {

    @Size(min = 3)
    @NotBlank
    private String name;

    @Size(min = 10)
    private String description;

    private String logo;

}
