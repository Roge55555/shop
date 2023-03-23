package com.effective.shop.sevice.impl;

import com.effective.shop.Utils;
import com.effective.shop.entity.Notification;
import com.effective.shop.repository.NotificationRepository;
import com.effective.shop.sevice.NotificationService;
import com.effective.shop.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final UserService userService;

    @Override
    public Notification add(Notification notification) {
        notification.setDateCreated(LocalDate.now());
        notification.setCreator(userService.findByLogin(Utils.getLogin()));
        notification.setReceiver(userService.findById(notification.getReceiver().getId()));

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getMyNotification() {
        return notificationRepository.findAllByReceiverId(userService.findByLogin(Utils.getLogin()).getId());
    }
}
