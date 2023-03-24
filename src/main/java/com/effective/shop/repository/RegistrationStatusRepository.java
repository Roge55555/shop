package com.effective.shop.repository;

import com.effective.shop.entity.RegistrationStatus;
import com.effective.shop.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationStatusRepository extends JpaRepository<RegistrationStatus, Long> {

    Optional<RegistrationStatus> findByName(Status name);
}
