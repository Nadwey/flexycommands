package pl.nadwey.flexycommands.argument.arguments;

import java.util.Collections;

import lombok.AllArgsConstructor;
import pl.nadwey.flexycommands.CommandContext;
import pl.nadwey.flexycommands.SuggestionContext;
import pl.nadwey.flexycommands.argument.*;

public class LiteralArgument extends ParentCommandArgument {
    public LiteralArgument(String name) {
        super(name);
    }

    @AllArgsConstructor
    private static class LiteralArgumentParseResult {
        private String remaining;
        private boolean valid;
    }

    private LiteralArgumentParseResult parse(String input) {
        int space = input.indexOf(' ');

        String entered;
        String remaining;

        if (space == -1) {
            entered = input;
            remaining = null;
        } else {
            entered = input.substring(0, space);
            remaining = input.substring(space + 1);
        }

        boolean valid = entered.equals(getName());

        return new LiteralArgumentParseResult(remaining, valid);
    }

    @Override
    public ParseResult parse(CommandContext context, String input) {
        LiteralArgumentParseResult result = parse(input);

        return new ParseResult(result.remaining, result.valid, result.valid);
    }

    @Override
    public SuggestionResult suggest(SuggestionContext context, String input) {
        LiteralArgumentParseResult result = parse(input);

        return new SuggestionResult(
                Collections.singletonList(getName()),
                result.remaining,
                result.valid
        );
    }
}
