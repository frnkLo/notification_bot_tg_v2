package com.secondbot.notification_bot;

import com.secondbot.notification_bot.configuration.TelegramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TelegramProperties.class) //для работы тележки
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
