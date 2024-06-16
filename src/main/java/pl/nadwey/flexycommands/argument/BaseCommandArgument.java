package pl.nadwey.flexycommands.argument;

import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseCommandArgument {
    @Getter
    private final String name;

    protected BaseCommandArgument(String name) {
        this.name = name;
    }

    public abstract ParseResult parse(CommandContext context, String input);

    public abstract SuggestionResult suggest(String input);

    public abstract boolean hasChildren();
}
