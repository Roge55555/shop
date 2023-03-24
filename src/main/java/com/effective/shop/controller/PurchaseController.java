package com.effective.shop.controller;

import com.effective.shop.entity.Product;
import com.effective.shop.entity.Purchase;
import com.effective.shop.model.dto.PurchaseDTO;
import com.effective.shop.sevice.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public Purchase addPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = Purchase.builder()
                .product(Product.builder().id(purchaseDTO.getProductId()).build())
                .amount(purchaseDTO.getAmount())
                .build();
        return purchaseService.add(purchase);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public List<Purchase> getAllPurchaseOfUser(@PathVariable("id") Long id) {
        return purchaseService.getAllPurchaseOfUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public void deletePurchase(@PathVariable("id") Long id) {
        purchaseService.delete(id);
    }
}
