package io.github.ericedwards.EricTestBot;

import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import io.github.ericedwards.EricTestBot.commands.PingCommand;
import io.github.ericedwards.EricTestBot.commands.RollCommand;
import io.github.ericedwards.EricTestBot.config.ConfigManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EricTestBotMain {

    private static Logger logger = LoggerFactory.getLogger(EricTestBotMain.class);

    public static void main(String[] args) {

        ConfigManager configManager = ConfigManager.instance();
        String token = configManager.getProperty("token");
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        /*
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
         */

        CommandHandler cmdHandler = new JavacordHandler(api);
        cmdHandler.registerCommand(new PingCommand());
        cmdHandler.registerCommand(new RollCommand());

    }

}
