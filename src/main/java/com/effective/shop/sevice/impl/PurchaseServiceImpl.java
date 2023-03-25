package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Product;
import com.effective.shop.entity.Purchase;
import com.effective.shop.exceptions.*;
import com.effective.shop.model.Role;
import com.effective.shop.repository.PurchaseRepository;
import com.effective.shop.sevice.ProductService;
import com.effective.shop.sevice.PurchaseService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ProductService productService;

    private final UserService userService;

    @Transactional(isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class,
            noRollbackFor = NoSuchElementException.class)
    @Override
    public Purchase add(Purchase purchase) {
        Product product = productService.findById(purchase.getProduct().getId());
        Double check;

        if (product.getAmount() >= purchase.getAmount()) {

            check = product.getPrice() * purchase.getAmount();
            if (Objects.nonNull(product.getDiscount())) {
                check *= (1 - (Double.valueOf(product.getDiscount().getAmount()) / 100));
            }

            if(userService.findByLogin(Utils.getLogin()).getBalance() < check) {
                throw new TooLowBalanceException("Not enough money on balance");
            }

            userService.changeBalance(userService.findByLogin(Utils.getLogin()).getBalance() - check, userService.findByLogin(Utils.getLogin()).getId());

            productService.updateAmount(product.getAmount() - purchase.getAmount(), userService.findByLogin(Utils.getLogin()).getId());

            purchase.setCreator(userService.findByLogin(Utils.getLogin()));
            purchase.setDate(LocalDateTime.now());

            return purchaseRepository.save(purchase);
        }
        else {
            throw new NotEnoughElementException("Not enough this product on the stock");
        }
    }

    @Override
    public Purchase findById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId).orElseThrow(() -> {
            throw new NoSuchElementException(purchaseId);
        });
    }

    @Override
    public List<Purchase> getAllPurchaseOfUser(Long userId) {
        if(userService.findById(userId).getLogin().equals(Utils.getLogin()) || userService.findByLogin(Utils.getLogin()).getRole().getName().equals(Role.ADMIN)) {
            return purchaseRepository.findAllByCreatorId(userId);
        }
        else {
            throw new TooLowAccessException("Only creator or moderator can see history!");
        }

    }

    @Override
    public void delete(Long purchaseId) {
        if (findById(purchaseId).getCreator().getLogin().equals(Utils.getLogin())) {
            if (findById(purchaseId).getDate().isBefore(LocalDateTime.now().minusDays(1))) {
                purchaseRepository.deleteById(purchaseId);
            }
            else {
                throw new TimeOutException("Out of time for cancel!");
            }
        }
        else {
            throw new TooLowAccessException("Only creator can cancel his purchase!");
        }
    }

    @Override
    public List<Long> findAllUserIdWhoBuyProductById(Long productId) {
        List<Purchase> purchaseList = purchaseRepository.findAllByProductId(productId);
        List<Long> idList = new ArrayList<>();
        for(Purchase purchase : purchaseList) {
            if (purchase.getDate().plusDays(1).isBefore(LocalDateTime.now())) {
                idList.add(purchase.getCreator().getId());
            }
        }
        return idList;
    }

}
