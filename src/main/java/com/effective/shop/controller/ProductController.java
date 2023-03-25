package com.effective.shop.controller;

import com.effective.shop.entity.Discount;
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
    public Product addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .amount(productDTO.getAmount())
                .tag(productDTO.getTag())
                .organization(Organization.builder().id(productDTO.getOrganizationId()).build())
                .discount(Discount.builder().id(productDTO.getDiscountId()).build())
                .build();
        return productService.add(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('user:permission')")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        Product product = Product.builder()
                .id(id)
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .amount(productDTO.getAmount())
                .tag(productDTO.getTag())
                .organization(Organization.builder().id(productDTO.getOrganizationId()).build())
                .discount(Discount.builder().id(productDTO.getDiscountId()).build())
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
