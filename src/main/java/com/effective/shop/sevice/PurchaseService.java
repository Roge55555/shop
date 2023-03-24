package com.effective.shop.sevice;

import com.effective.shop.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase add(Purchase purchase);

    Purchase findById(Long purchaseId);

    List<Purchase> getAllPurchaseOfUser(Long userId);

    void delete(Long purchaseId);
}
