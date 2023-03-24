package com.effective.shop.controller;

import com.effective.shop.entity.Organization;
import com.effective.shop.model.dto.OrganizationDTO;
import com.effective.shop.sevice.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public Organization addOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        Organization organization = Organization.builder()
                .name(organizationDTO.getName())
                .description(organizationDTO.getDescription())
                .logo(organizationDTO.getLogo())
                .build();
        return organizationService.add(organization);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('user:permission')")
    public void updateOrganization(@PathVariable("id") Long id, @RequestBody OrganizationDTO organizationDTO) {
        Organization organization = Organization.builder()
                .id(id)
                .name(organizationDTO.getName())
                .description(organizationDTO.getDescription())
                .logo(organizationDTO.getLogo())
                .build();
        organizationService.update(organization);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public Organization getById(@PathVariable("id") Long id) {
        return organizationService.findById(id);
    }
}
