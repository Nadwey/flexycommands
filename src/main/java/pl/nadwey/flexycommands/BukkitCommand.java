package pl.nadwey.flexycommands;

import java.util.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pl.nadwey.flexycommands.argument.*;

public class BukkitCommand extends Command {

    private final BaseCommand command;

    public BukkitCommand(BaseCommand command) {
        super(command.getName());
        this.command = command;
    }

    //    private List<String> parseArgs(String[] args) {
    //        String input = String.join(" ", args);
    //
    //        List<String> arguments = new ArrayList<>();
    //
    //        int current = 0;
    //
    //        boolean inQuotes = false;
    //        boolean escape = false;
    //
    //        while (current < input.length()) {
    //            StringBuilder argument = new StringBuilder();
    //
    //            while (current < input.length()) {
    //                char c = input.charAt(current);
    //
    //                System.out.println(c);
    //
    //                if (c == '\\' && !escape) {
    //                    escape = true;
    //                }
    //                else if (c == '"' && !escape) {
    //                    inQuotes = !inQuotes;
    //
    //                }
    //                else if (c == ' ' && !inQuotes && !escape) {
    //                    current++;
    //                    break;
    //                } else {
    //                    argument.append(c);
    //                    escape = false;
    //                }
    //                current++;
    //            }
    //
    //            arguments.add(argument.toString());
    //        }
    //
    //        return arguments;
    //    }

    @Override
    public @NotNull List<String> tabComplete(
            @NotNull CommandSender sender,
            @NotNull String alias,
            String[] strings
    ) {
        String input = String.join(" ", strings);

        List<String> suggestions = new ArrayList<>();

        for (BaseCommandArgument argument : this.command.getArguments()) {
            SuggestionResult result = argument.suggest(input);

            if (result.isShouldContinue()) {
                if (argument instanceof ParentCommandArgument && argument.hasChildren()) {
                    ParentCommandArgument parentArgument = (ParentCommandArgument) argument;
                    return parentArgument.suggestChildren(result.getRemaining());
                }

                return Collections.emptyList();
            }

            suggestions.addAll(result.getSuggestions());
        }

        return suggestions;
    }

    @Override
    public boolean execute(
            @NotNull CommandSender commandSender,
            @NotNull String s,
            @NotNull String[] strings
    ) {
        String input = String.join(" ", strings);

        CommandContext context = new CommandContext(commandSender);

        for (BaseCommandArgument argument : this.command.getArguments()) {
            ParseResult result = argument.parse(context, input);

            if (result.isShouldContinue()) {
                if (argument instanceof ParentCommandArgument && argument.hasChildren()) {
                    ParentCommandArgument parentArgument = (ParentCommandArgument) argument;
                    parentArgument.parseChildren(context, result.getRemaining());
                }
            } else if (result.isValid() && argument.getExecutor() != null) {
                argument.getExecutor().execute(context);
            }
        }

        return true;
    }
}
