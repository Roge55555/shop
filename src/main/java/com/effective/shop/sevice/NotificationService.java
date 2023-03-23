package com.effective.shop.sevice;

import com.effective.shop.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification add(Notification notification);

    List<Notification> getMyNotification();
}
