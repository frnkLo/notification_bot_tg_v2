package com.secondbot.notification_bot.repository;

import com.secondbot.notification_bot.entity.Notification;
import com.secondbot.notification_bot.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, UUID> {
    List<Notification> findAllByStatusAndSendAtBefore(Status status, LocalDateTime time);
}
