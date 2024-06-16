package pl.nadwey.flexycommands.argument.arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.nadwey.flexycommands.argument.PCCommandArgument;
import pl.nadwey.flexycommands.argument.ParseResult;
import pl.nadwey.flexycommands.argument.SuggestionResult;
import pl.nadwey.flexycommands.argument.VirtualParseResult;

public class LiteralArgument extends PCCommandArgument {

    public LiteralArgument(String name) {
        super(name);
    }

    @Override
    public ParseResult parse(String input) {
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

        return new VirtualParseResult(null, remaining, valid, valid);
    }

    @Override
    public SuggestionResult suggest(String input) {
        ParseResult result = parse(input);

        return new SuggestionResult(
                Collections.singletonList(getName()),
                result.getRemaining(),
                result.isValid()
        );
    }
}
