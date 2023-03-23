package com.effective.shop.sevice.impl;

import com.effective.shop.entity.User;
import com.effective.shop.model.Role;
import com.effective.shop.repository.UserRepository;
import com.effective.shop.sevice.AccessRoleService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AccessRoleService accessRoleService;

    public User add(User user) {
        //TODO coding password before save
        user.setBalance(0D);
        user.setRole(accessRoleService.findByName(Role.USER));

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow();
    }

    @Override
    public User update(User user) {//TODO realise
        return user;
    }

    @Override
    public void delete() {//TODO realise after adding JWT, create static Utils for login
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {//TODO realise after adding JWT
    }

}
