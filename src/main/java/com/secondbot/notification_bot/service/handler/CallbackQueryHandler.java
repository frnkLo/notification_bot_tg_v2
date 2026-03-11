package com.secondbot.notification_bot.service.handler;


import com.secondbot.notification_bot.bot.Bot;
import com.secondbot.notification_bot.service.contract.AbstractHandler;
import com.secondbot.notification_bot.service.manager.MainManager;
import com.secondbot.notification_bot.service.manager.notification.NotificationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler extends AbstractHandler {

    public static final String CALLBACK_NOTIFICATION = "NOTIFICATION";
    public static final String CALLBACK_MAIN = "MAIN";

    NotificationManager notificationManager;
    MainManager  mainManager;

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) throws TelegramApiException {
        var query = (CallbackQuery) object;
        String[] words = query.getData().split("_");
        switch (words[0]) {
            case CALLBACK_NOTIFICATION -> {
                return notificationManager.answerQuery(query, words, bot);
            }
            case CALLBACK_MAIN -> {
                return mainManager.answerQuery(query, words, bot);
            }
            default ->
                throw new UnsupportedOperationException("Неизвестный callback data: " + words[0]);

        }
    }
}
