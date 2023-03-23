package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.User;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.exceptions.TooLowAccessException;
import com.effective.shop.model.Role;
import com.effective.shop.repository.UserRepository;
import com.effective.shop.sevice.AccessRoleService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AccessRoleService accessRoleService;

    private final PasswordEncoder passwordEncoder;

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBalance(0D);
        user.setRole(accessRoleService.findByName(Role.USER));

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
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
    public void delete(Long id) {
        if(findByLogin(Utils.getLogin()).getId().equals(id) ||
                findByLogin(Utils.getLogin()).getRole().getId() < findById(id).getRole().getId()) {
            userRepository.deleteById(id);
        }
        else {
            throw new TooLowAccessException("You can delete yourself account or account with lower permissions!");
        }
    }

    @Override
    public void changeBalance(Double balance, Long userId) {
            User user = findById(userId);
            user.setBalance(balance);
            userRepository.save(user);
    }

    @Override
    public void changeAccess(Long id, String role) {
        List<Role> list = Arrays.stream(Role.values()).collect(Collectors.toList());
        User user;
        try {
            list.contains(Role.valueOf(role.toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(role);
        }
        if(findByLogin(Utils.getLogin()).getRole().getId() <= accessRoleService.findByName(Role.valueOf(role.toUpperCase(Locale.ROOT))).getId()) {
            user = findById(id);
            user.setRole(accessRoleService.findByName(Role.valueOf(role.toUpperCase(Locale.ROOT))));
        }
        else {
            throw new TooLowAccessException("You can`t up access higher then your`s. Your - " + findByLogin(Utils.getLogin()).getRole().getName() + ", you want set - " + role + ".");
        }

        userRepository.save(user);
    }

}
