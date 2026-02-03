package com.secondbot.notification_bot.service.contract;

import com.secondbot.notification_bot.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface MessageListener {

    BotApiMethod<?> answerMessage(Message message, Bot bot) throws TelegramApiException;
}
