package com.effective.shop.controller;

import com.effective.shop.entity.Organization;
import com.effective.shop.entity.Product;
import com.effective.shop.model.dto.ProductDTO;
import com.effective.shop.sevice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:permission')")
    public Product addProduct(@Valid @RequestBody ProductDTO purchaseDTO) {
        Product product = Product.builder()
                .name(purchaseDTO.getName())
                .description(purchaseDTO.getDescription())
                .price(purchaseDTO.getPrice())
                .amount(purchaseDTO.getAmount())
                .tag(purchaseDTO.getTag())
                .organization(Organization.builder().id(purchaseDTO.getOrganizationId()).build())
                .build();
        return productService.add(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('admin:permission')")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO purchaseDTO) {
        Product product = Product.builder()
                .id(id)
                .name(purchaseDTO.getName())
                .description(purchaseDTO.getDescription())
                .price(purchaseDTO.getPrice())
                .amount(purchaseDTO.getAmount())
                .tag(purchaseDTO.getTag())
                .organization(Organization.builder().id(purchaseDTO.getOrganizationId()).build())
                .build();
        productService.update(product);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public Product getById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

}
