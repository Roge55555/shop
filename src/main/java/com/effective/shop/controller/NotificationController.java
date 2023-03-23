package com.effective.shop.controller;

import com.effective.shop.entity.Notification;
import com.effective.shop.entity.User;
import com.effective.shop.model.dto.NotificationDTO;
import com.effective.shop.sevice.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:permission')")
    public Notification addSessionRating(@Valid @RequestBody NotificationDTO notificationDTO) {
        Notification notification = Notification.builder()
                .receiver(User.builder().id(notificationDTO.getReceiverId()).build())
                .title(notificationDTO.getTitle())
                .message(notificationDTO.getMessage())
                .build();
        return notificationService.add(notification);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('user:permission')")
    public List<Notification> getAllUsers() {
        return notificationService.getMyNotification();
    }
}
