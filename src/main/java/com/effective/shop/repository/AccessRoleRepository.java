package com.effective.shop.repository;

import com.effective.shop.entity.AccessRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRoleRepository extends JpaRepository<AccessRole, Long> {
}
