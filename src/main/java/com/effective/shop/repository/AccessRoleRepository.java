package com.effective.shop.repository;

import com.effective.shop.entity.AccessRole;
import com.effective.shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRoleRepository extends JpaRepository<AccessRole, Long> {

    Optional<AccessRole> findByName(Role name);
}
