package pl.nadwey.flexycommands.argument.arguments;

import pl.nadwey.flexycommands.CommandContext;
import pl.nadwey.flexycommands.SuggestionContext;
import pl.nadwey.flexycommands.argument.*;
import pl.nadwey.flexycommands.utility.TextUtils;

import java.util.*;

public class LiteralTreeArgument extends ParentCommandArgument {
    private final Map<String, BaseCommandArgument> paths = new HashMap<>();

    public LiteralTreeArgument(String name) {
        super(name);
    }

    @Override
    public ParseResult parse(CommandContext context, String input) {
        int space = input.indexOf(' ');

        if (space == -1) {
            return new ParseResult(input, false, false);
        }

        String enteredName = input.substring(0, space);
        input = input.substring(space + 1);

        context.set(getName(), enteredName);

        if (paths.containsKey(enteredName)) {
            BaseCommandArgument argument = paths.get(enteredName);
            if (argument == null) {
                return new ParseResult(input, true, true);
            }

            return argument.parse(context, input);
        }

        return new ParseResult("", false, false);
    }

    @Override
    public SuggestionResult suggest(SuggestionContext context, String input) {
        int space = input.indexOf(' ');

        if (space == -1) {
            return new SuggestionResult(TextUtils.filterStartingWith(paths.keySet(), input), "", false);
        }

        String enteredName = input.substring(0, space);
        input = input.substring(space + 1);

        if (paths.containsKey(enteredName)) {
            BaseCommandArgument argument = paths.get(enteredName);
            if (argument == null) {
                return new SuggestionResult(Collections.emptyList(), input, true);
            }

            return argument.suggest(context, input);
        }

        return new SuggestionResult(Collections.emptyList(), "", false);
    }

    public LiteralTreeArgument path(String name, BaseCommandArgument argument) {
        if (paths.containsKey(name)) {
            throw new IllegalArgumentException(name + " already exists");
        }

        paths.put(name, argument);

        return this;
    }

    public LiteralTreeArgument path(String name) {
        if (paths.containsKey(name)) {
            throw new IllegalArgumentException(name + " already exists");
        }

        paths.put(name, null);

        return this;
    }
}
