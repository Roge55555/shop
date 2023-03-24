package com.effective.shop.sevice.impl;

import com.effective.shop.entity.Product;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.model.Status;
import com.effective.shop.repository.ProductRepository;
import com.effective.shop.sevice.OrganizationService;
import com.effective.shop.sevice.ProductService;
import com.effective.shop.sevice.RegistrationStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final RegistrationStatusService registrationStatusService;

    private final OrganizationService organizationService;

    @Override
    public Product add(Product product) {
        product.setStatus(registrationStatusService.findByName(Status.CONSIDERATION));
        product.setOrganization(organizationService.findById(product.getOrganization().getId()));

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException(id);
        });
    }

    @Override
    public Product update(Product product) {
        Product updatedProduct = findById(product.getId());

        if (Objects.nonNull(product.getAmount())) {
            updatedProduct.setAmount(product.getAmount());
        }
        if (Objects.nonNull(product.getDescription())) {
            updatedProduct.setDescription(product.getDescription());
        }
        if (Objects.nonNull(product.getName())) {
            updatedProduct.setName(product.getName());
        }
        if (Objects.nonNull(product.getOrganization().getId())) {
            updatedProduct.setOrganization(organizationService.findById(product.getOrganization().getId()));
        }
        if (Objects.nonNull(product.getPrice())) {
            updatedProduct.setPrice(product.getPrice());
        }
        if (Objects.nonNull(product.getTag())) {
            updatedProduct.setTag(product.getTag());
        }

        return productRepository.save(updatedProduct);
    }

    @Transactional
    @Override
    public Product updateAmount(Long amount, Long id) {
        Product product = findById(id);
        product.setAmount(amount);
        return productRepository.save(product);
    }

    @Override
    public Product updateStatus(Long id, String status) {
        Product product = findById(id);
        product.setStatus(registrationStatusService.findByName(Status.valueOf(status.toUpperCase(Locale.ROOT))));
        return productRepository.save(product);
    }
}
