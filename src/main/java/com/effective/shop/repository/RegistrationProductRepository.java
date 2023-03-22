package com.effective.shop.repository;

import com.effective.shop.entity.RegistrationProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RegistrationProductRepository extends JpaRepository<RegistrationProduct, Long> {
}
