package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Product;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.exceptions.TooLowAccessException;
import com.effective.shop.model.Role;
import com.effective.shop.model.Status;
import com.effective.shop.repository.ProductRepository;
import com.effective.shop.sevice.*;
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

    private final UserService userService;

    private final DiscountService discountService;

    @Override
    public Product add(Product product) {
        if((organizationService.findById(product.getOrganization().getId()).getCreator().getLogin().equals(Utils.getLogin()) &&
                organizationService.findById(product.getOrganization().getId()).getStatus().getName().equals(Status.APPROVED)) ||
                userService.findByLogin(Utils.getLogin()).getRole().getName().equals(Role.ADMIN)) {
            product.setStatus(registrationStatusService.findByName(Status.CONSIDERATION));
            product.setOrganization(organizationService.findById(product.getOrganization().getId()));
            product.setDiscount(discountService.findById(product.getDiscount().getId()));

            return productRepository.save(product);
        }
        else {
            throw new TooLowAccessException("Only creator of approved community and admin can create products");
        }
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
        if((organizationService.findById(product.getOrganization().getId()).getCreator().getLogin().equals(Utils.getLogin()) &&
                organizationService.findById(product.getOrganization().getId()).getStatus().getName().equals(Status.APPROVED)) ||
                userService.findByLogin(Utils.getLogin()).getRole().getName().equals(Role.ADMIN)) {
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
            if (Objects.nonNull(product.getDiscount().getId())) {
                updatedProduct.setDiscount(discountService.findById(product.getDiscount().getId()));
            }
            if (Objects.nonNull(product.getPrice())) {
                updatedProduct.setPrice(product.getPrice());
            }
            if (Objects.nonNull(product.getTag())) {
                updatedProduct.setTag(product.getTag());
            }

            return productRepository.save(updatedProduct);
        }
        else {
            throw new TooLowAccessException("Only creator of approved community and admin can create products");
        }
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
