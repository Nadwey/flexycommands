package pl.nadwey.flexycommands.argument.arguments;

import pl.nadwey.flexycommands.CommandContext;
import pl.nadwey.flexycommands.SuggestionContext;
import pl.nadwey.flexycommands.argument.BaseCommandArgument;
import pl.nadwey.flexycommands.argument.ParseResult;
import pl.nadwey.flexycommands.argument.SuggestionResult;

import java.util.Collections;

public class GreedyStringArgument extends BaseCommandArgument {
    protected GreedyStringArgument(String name) {
        super(name);
    }

    @Override
    public ParseResult parse(CommandContext context, String input) {
        context.set(getName(), input);

        return new ParseResult("", true, false);
    }

    @Override
    public SuggestionResult suggest(SuggestionContext context, String input) {
        return new SuggestionResult(Collections.singletonList("<" + getName() + ">"), "", false);
    }
}
