package pl.nadwey.flexycommands.argument.arguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.nadwey.flexycommands.argument.PCCommandArgument;
import pl.nadwey.flexycommands.argument.ParseResult;
import pl.nadwey.flexycommands.argument.SuggestionResult;

public class StringArgument extends PCCommandArgument {

    public StringArgument(String name) {
        super(name);
    }

    @Override
    public ParseResult parse(String value) {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        boolean shouldContinue = false;

        for (; i < value.length(); i++) {
            char c = value.charAt(i);

            if (c == ' ') {
                i++;
                shouldContinue = true;
                break;
            }

            builder.append(c);
        }

        return new ParseResult(
                builder.toString(),
                value.substring(i),
                true,
                shouldContinue
        );
    }

    @Override
    public SuggestionResult suggest(String value) {
        ParseResult result = parse(value);

        return new SuggestionResult(
                Collections.emptyList(),
                result.getRemaining(),
                result.isShouldContinue()
        );
    }
}
