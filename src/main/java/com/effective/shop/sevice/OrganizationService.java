package com.effective.shop.sevice;

import com.effective.shop.entity.Organization;

public interface OrganizationService {

    Organization add(Organization organization);

    Organization findById(Long Id);

    Organization update(Organization organization);

    Organization updateStatus(Long id, String status);
}
