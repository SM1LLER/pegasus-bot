package by.pikeylab.pegasusbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:test_bot.properties")
@ConfigurationProperties
public class BotProperties {

    private String discordToken;
    private String guildId;
    private String testChannelId;
    private String ownerId;

    public String getDiscordToken() {
        return discordToken;
    }

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getTestChannelId() {
        return testChannelId;
    }

    public void setTestChannelId(String testChannelId) {
        this.testChannelId = testChannelId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

}
