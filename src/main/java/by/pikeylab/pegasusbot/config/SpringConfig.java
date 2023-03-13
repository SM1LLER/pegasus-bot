package by.pikeylab.pegasusbot.config;

import org.springframework.context.annotation.Configuration;

public class SpringConfig {
    private final BotProperties properties;

    public SpringConfig(BotProperties properties) {
        this.properties = properties;
    }
}
