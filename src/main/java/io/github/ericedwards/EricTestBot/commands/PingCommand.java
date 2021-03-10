package io.github.ericedwards.EricTestBot.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingCommand implements CommandExecutor {

    private static Logger logger = LoggerFactory.getLogger(PingCommand.class);

    @Command(aliases = {"!ping"}, description = "Pong!")
    public String onCommand(DiscordApi api, Message message) {
        logger.debug("ping");
        return "Pong!";
    }

}
