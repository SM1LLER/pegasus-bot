package by.pikeylab.pegasusbot.main;

import by.pikeylab.pegasusbot.command.ConfirmationCommand;
import by.pikeylab.pegasusbot.config.BotProperties;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(BotProperties.class)
@SpringBootApplication
public class PegasusBotApplication implements CommandLineRunner {

	static Logger logger = LoggerFactory.getLogger(PegasusBotApplication.class);
	@Autowired
	private final BotProperties botProperties;

	public PegasusBotApplication(BotProperties properties) {
		this.botProperties = properties;
	}

	public static void main(String[] args) {
		SpringApplication.run(PegasusBotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CommandClient commandClient = buildCommandClient();
		JDA jda = JDABuilder.createDefault(this.botProperties.getDiscordToken())
				.addEventListeners(commandClient)
				.addEventListeners(new ConfirmationCommand.ButtonHandler())
				.setStatus(OnlineStatus.ONLINE)
				.build();
		jda.awaitReady();
		jda.updateCommands();
		jda.getPresence().setActivity(Activity.playing("Следит за сервером"));
	}

	private CommandClient buildCommandClient() {
		CommandClientBuilder builder = new CommandClientBuilder();
		builder.forceGuildOnly(this.botProperties.getGuildId())
				.setOwnerId(this.botProperties.getOwnerId())
				.addSlashCommand(new ConfirmationCommand());
		return builder.build();
	}
}
