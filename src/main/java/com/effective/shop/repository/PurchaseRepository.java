package com.effective.shop.repository;

import com.effective.shop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByCreatorId(Long receiverId);

    List<Purchase> findAllByProductId(Long receiverId);
}
