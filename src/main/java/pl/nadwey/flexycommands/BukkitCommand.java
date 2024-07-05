package pl.nadwey.flexycommands;

import java.util.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BukkitCommand extends Command {

    private final BaseCommand command;

    public BukkitCommand(BaseCommand command) {
        super(command.getName());
        this.command = command;
    }

    @Override
    public @NotNull List<String> tabComplete(
            @NotNull CommandSender sender,
            @NotNull String alias,
            String[] strings
    ) {
        String input = String.join(" ", strings);
        SuggestionContext context = new SuggestionContext(sender);

        return this.command.suggestChildren(context, input);
    }

    @Override
    public boolean execute(
            @NotNull CommandSender commandSender,
            @NotNull String s,
            @NotNull String[] strings
    ) {
        String input = String.join(" ", strings);
        CommandContext context = new CommandContext(commandSender);

         if(!commandSender.hasPermission(command.getPermission())) {
             return false;
         }

        return this.command.executeChildren(context, input);
    }
}
