package com.secondbot.notification_bot.service.manager.notification;

import com.secondbot.notification_bot.bot.Bot;
import com.secondbot.notification_bot.entity.Notification;
import com.secondbot.notification_bot.entity.Status;
import com.secondbot.notification_bot.repository.NotificationRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class NotificationContainer {
    Bot bot;
    NotificationRepo notificationRepo;

    private static final String REMINDER = "⚡️\uFE0F Напоминание: ";
    private static final String REMINDER_EMOJI = "❗️\uFE0F ";

    @Scheduled(fixedDelay = 10000)
    public void checkAndSendNotifications() {

        List<Notification> pendingNotifications = notificationRepo
                .findAllByStatusAndSendAtBefore(Status.WAITING, LocalDateTime.now());

        if (pendingNotifications.isEmpty()) {
            return;
        }

        for (Notification notification : pendingNotifications) {
            try {
                bot.execute(
                        SendMessage.builder()
                                .chatId(notification.getUser().getChatId())
                                .text(REMINDER + notification.getTitle() + "\n"
                                        + REMINDER_EMOJI + notification.getDescription() + "\n\n")
                                .build()
                );


                notification.setStatus(Status.FINISHED);
                notificationRepo.save(notification);

            } catch (TelegramApiException e) {
                log.error("Ошибка при отправке уведомления {}: {}", notification.getId(), e.getMessage());

                notification.setStatus(Status.FINISHED);
                notificationRepo.save(notification);

            }
        }
    }
}

