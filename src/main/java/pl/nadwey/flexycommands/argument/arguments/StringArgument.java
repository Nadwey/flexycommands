package pl.nadwey.flexycommands.argument.arguments;

import java.util.Collections;

import lombok.AllArgsConstructor;
import pl.nadwey.flexycommands.CommandContext;
import pl.nadwey.flexycommands.argument.ParentCommandArgument;
import pl.nadwey.flexycommands.argument.ParseResult;
import pl.nadwey.flexycommands.argument.SuggestionResult;

public class StringArgument extends ParentCommandArgument {
    public StringArgument(String name) {
        super(name);
    }

    @AllArgsConstructor
    static
    class StringArgumentParseResult {
        String entered;
        String remaining;
        boolean shouldContinue;
    }

    StringArgumentParseResult parse(String input) {
        StringBuilder builder = new StringBuilder();

        int space = 0;
        boolean shouldContinue = false;

        for (; space < input.length(); space++) {
            char c = input.charAt(space);

            if (c == ' ') {
                space++;
                shouldContinue = true;
                break;
            }

            builder.append(c);
        }

        return new StringArgumentParseResult(builder.toString(), input.substring(space), shouldContinue);
    }

    @Override
    public ParseResult parse(CommandContext context, String input) {
        StringArgumentParseResult result = parse(input);

        context.set(getName(), result.entered);

        return new ParseResult(result.remaining, true, result.shouldContinue);
    }

    @Override
    public SuggestionResult suggest(String value) {
        StringArgumentParseResult result = parse(value);

        return new SuggestionResult(
                Collections.emptyList(),
                result.remaining,
                result.shouldContinue
        );
    }
}
