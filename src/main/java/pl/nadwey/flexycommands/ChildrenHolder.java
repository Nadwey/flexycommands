package pl.nadwey.flexycommands;

import org.bukkit.command.CommandSender;
import pl.nadwey.flexycommands.argument.BaseCommandArgument;
import pl.nadwey.flexycommands.argument.ParentCommandArgument;
import pl.nadwey.flexycommands.argument.ParseResult;
import pl.nadwey.flexycommands.argument.SuggestionResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface ChildrenHolder {
    List<BaseCommandArgument> getChildren();

    static boolean hasPermission(CommandSender sender, String permission) {
        // if permission isn't provided we assume that the command can be executed by anyone
        // TODO: make this configurable
        if (permission == null || permission.isEmpty()) return true;

        return sender.hasPermission(permission);
    }

    default List<String> suggestChildren(SuggestionContext context, String input) {
        List<String> suggestions = new ArrayList<>();

        for (BaseCommandArgument children : getChildren()) {
            if (!hasPermission(context.getSender(), children.getPermission()))
                continue;

            SuggestionResult result = children.suggest(context, input);

            if (result.isShouldContinue()) {
                if (children instanceof ParentCommandArgument) {
                    ParentCommandArgument parentArgument = (ParentCommandArgument) children;

                    if (parentArgument.hasChildren()) {
                        return parentArgument.suggestChildren(context, result.getRemaining());
                    }
                }

                return Collections.emptyList();
            }

            suggestions.addAll(result.getSuggestions());
        }

        return suggestions;
    }

    default boolean executeChildren(CommandContext context, String input) {
        for (BaseCommandArgument children : getChildren()) {
            if (!hasPermission(context.getSender(), children.getPermission()))
                continue;

            ParseResult result = children.parse(context, input);

            if (result.isShouldContinue()) {
                if (children instanceof ParentCommandArgument) {
                    ParentCommandArgument parentArgument = (ParentCommandArgument) children;

                    if (parentArgument.hasChildren()) {
                        parentArgument.executeChildren(context, result.getRemaining());
                    }
                }
            } else if (result.isValid() && children.getExecutor() != null) {
                children.getExecutor().execute(context);
            }
        }

        return true;
    }
}
