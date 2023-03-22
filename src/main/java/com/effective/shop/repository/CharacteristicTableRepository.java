package com.effective.shop.repository;

import com.effective.shop.entity.CharacteristicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicTableRepository extends JpaRepository<CharacteristicTable, Long> {
}
