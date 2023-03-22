package com.effective.shop.repository;

import com.effective.shop.entity.RegistrationOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationOrganizationRepository extends JpaRepository<RegistrationOrganization, Long> {
}
