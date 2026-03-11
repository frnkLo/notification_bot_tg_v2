package com.secondbot.notification_bot.service.handler;

import com.secondbot.notification_bot.bot.Bot;
import com.secondbot.notification_bot.service.contract.AbstractHandler;
import com.secondbot.notification_bot.service.manager.MainManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class CommandHandler extends AbstractHandler {

    public static final String COMMAND_START = "/start";

    MainManager mainManager;

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        var message = (Message) object;
        if (COMMAND_START.equals(message.getText())) {
            return mainManager.answerCommand(message, bot);
        }
        throw new UnsupportedOperationException();
    }
}
