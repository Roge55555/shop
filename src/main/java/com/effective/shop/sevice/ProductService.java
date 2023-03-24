package com.effective.shop.sevice;

import com.effective.shop.entity.Product;

public interface ProductService {

    Product add(Product product);

    Product findById(Long id);

    Product update(Product product);

    Product updateAmount(Long amount, Long id);

    Product updateStatus(Long id, String status);

}
