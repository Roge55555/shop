package com.effective.shop.sevice;

import com.effective.shop.entity.User;

import java.util.List;

public interface UserService {

    User add(User user);

    List<User> findAll();

    User findById(Long id);

    User findByLogin(String login);

    void delete(Long id);

    void changeBalance(Double balance, Long userId);

    void changeAccess(Long id, String role);
}
