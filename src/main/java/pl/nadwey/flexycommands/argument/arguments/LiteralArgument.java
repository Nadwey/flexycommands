package pl.nadwey.flexycommands.argument.arguments;

import java.util.Collections;

import lombok.AllArgsConstructor;
import pl.nadwey.flexycommands.CommandContext;
import pl.nadwey.flexycommands.argument.*;

public class LiteralArgument extends ParentCommandArgument {
    public LiteralArgument(String name) {
        super(name);
    }

    @AllArgsConstructor
    private class LiteralArgumentParseResult {
        private String entered;
        private String remaining;
        private boolean valid;
    }

    private LiteralArgumentParseResult parse(String input) {
        int i = input.indexOf(' ');

        String entered;
        String remaining;

        if (i == -1) {
            entered = input;
            remaining = input;
        } else {
            entered = input.substring(0, i);
            remaining = input.substring(i + 1);
        }

        boolean valid = entered.equals(getName());

        return new LiteralArgumentParseResult(entered, remaining, valid);
    }

    @Override
    public ParseResult parse(CommandContext context, String input) {
        LiteralArgumentParseResult result = parse(input);

        return new ParseResult(result.remaining, result.valid, result.valid);
    }

    @Override
    public SuggestionResult suggest(String input) {
        LiteralArgumentParseResult result = parse(input);

        return new SuggestionResult(
                Collections.singletonList(getName()),
                result.remaining,
                result.valid
        );
    }
}
