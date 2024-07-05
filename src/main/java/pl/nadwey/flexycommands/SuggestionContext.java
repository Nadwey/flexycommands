package pl.nadwey.flexycommands;

import lombok.Getter;
import org.bukkit.command.CommandSender;

public class SuggestionContext {
    @Getter
    private final CommandSender sender;

    public SuggestionContext(CommandSender sender) {
        this.sender = sender;
    }
}
