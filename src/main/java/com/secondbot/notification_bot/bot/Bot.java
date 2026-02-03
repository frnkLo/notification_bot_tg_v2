package com.secondbot.notification_bot.bot;

import com.secondbot.notification_bot.configuration.TelegramProperties;
import com.secondbot.notification_bot.service.UpdateDispatcher;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component //аннотации сервис и компонент одно и то же
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Bot extends TelegramWebhookBot {

    TelegramProperties telegramProperties;
    UpdateDispatcher updateDispatcher;

    public Bot(TelegramProperties telegramProperties, UpdateDispatcher updateDispatcher) {
        super(telegramProperties.getToken()); //таким образом передаем токен нашему боту
        this.telegramProperties = telegramProperties;
        this.updateDispatcher = updateDispatcher;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return updateDispatcher.distribute(update, this);
    }

    @Override
    public String getBotPath() {
        return telegramProperties.getUrl(); //переопредили для работы бота
    }

    @Override
    public String getBotUsername() {
        return telegramProperties.getName(); //переопредили для работы бота
    }
}
