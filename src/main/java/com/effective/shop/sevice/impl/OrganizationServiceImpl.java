package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Organization;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.model.Status;
import com.effective.shop.repository.OrganizationRepository;
import com.effective.shop.sevice.OrganizationService;
import com.effective.shop.sevice.RegistrationStatusService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final UserService userService;

    private final RegistrationStatusService registrationStatusService;

    @Override
    public Organization add(Organization organization) {
        organization.setDateCreated(LocalDate.now());
        organization.setCreator(userService.findByLogin(Utils.getLogin()));
        organization.setStatus(registrationStatusService.findByName(Status.CONSIDERATION));

        return organizationRepository.save(organization);
    }

    @Override
    public Organization findById(Long id) {
        return organizationRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException(id);
        });
    }

    @Override
    public Organization update(Organization organization) {
        Organization updatedOrganization = findById(organization.getId());

        if (Objects.nonNull(organization.getDescription())) {
            updatedOrganization.setDescription(organization.getDescription());
        }
        if (Objects.nonNull(organization.getLogo())) {
            updatedOrganization.setLogo(organization.getLogo());
        }
        if (Objects.nonNull(organization.getName())) {
            updatedOrganization.setName(organization.getName());
        }

        return organizationRepository.save(updatedOrganization);
    }

    @Override
    public Organization updateStatus(Long id, String status) {
        Organization organization = findById(id);
        organization.setStatus(registrationStatusService.findByName(Status.valueOf(status.toUpperCase(Locale.ROOT))));
        return organizationRepository.save(organization);
    }
}
