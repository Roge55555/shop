package com.effective.shop.sevice;

import com.effective.shop.entity.User;

public interface UserService {

    User add(User user);

    User findById(Long id);

    User findByLogin(String login);

    User update(User user);

    void delete();

    void changePassword(String oldPassword, String newPassword);
}
