package by.pikeylab.pegasusbot.command;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class ConfirmationCommand extends SlashCommand {

    public ConfirmationCommand() {
        this.name = "confirmation"; // This has to be lowercase
        this.help = "Performs a ping to see the bot's delay";
    }

    @Override
    public void execute(SlashCommandEvent event) {
        EmbedBuilder warningEmbed = new EmbedBuilder();
        warningEmbed.setTitle("Пользователь Эдуард совершил нарушение")
                        .setDescription("Отправил сообщение: **пук**");
        event.replyEmbeds(warningEmbed.build())
                .addActionRow(
                        Button.danger("block", "Заблокировать")
                )
                .queue();
    }

    public static class ButtonHandler extends ListenerAdapter {

        @Override
        public void onButtonInteraction(ButtonInteractionEvent event) {
            switch (event.getComponentId()) {
                case "block":
                    ActionRow confirmationActionRow = ActionRow.of(
                            Button.primary("confirm", "Подтвердить"),
                            Button.secondary("cancel", "отмена")
                    );
                    event.editComponents(confirmationActionRow)
                            .queue();
                    break;
                case "confirm":
                    event.editComponents().queue(interactionHook -> {
                        EmbedBuilder successEmbed = new EmbedBuilder();
                        successEmbed.setTitle("Пользователь успешно заблокирован", null);
                        interactionHook.sendMessageEmbeds(successEmbed.build()).queue();
                    });
                    break;
                case "cancel":
                    ActionRow blockActionRow = ActionRow.of(
                            Button.danger("block", "Заблокировать")
                    );
                    event.editComponents(blockActionRow)
                            .queue();
                    break;
            }
        }
    }

}
