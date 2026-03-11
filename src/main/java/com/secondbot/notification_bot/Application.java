package com.secondbot.notification_bot;

import com.secondbot.notification_bot.configuration.TelegramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(TelegramProperties.class)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
