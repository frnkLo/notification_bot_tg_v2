package com.secondbot.notification_bot.configuration;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bot") //инджектит в филды класса значения из aplication пропертей с указан префиксом
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramProperties {

    //благодаря конфиг проперт по префиксу бот в значения ниже будут вставлены значения из пропертей
    String url;
    String token;
    String name;
}
