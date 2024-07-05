package pl.nadwey.flexycommands.argument;

import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;
import pl.nadwey.flexycommands.SuggestionContext;

@Getter
public abstract class BaseCommandArgument {
    private final String name;
    private String permission;
    private CommandExecutor executor;

    protected BaseCommandArgument(String name) {
        this.name = name;
    }

    public abstract ParseResult parse(CommandContext context, String input);

    public abstract SuggestionResult suggest(SuggestionContext context, String input);

    public BaseCommandArgument execute(CommandExecutor executor) {
        this.executor = executor;

        return this;
    }

    public BaseCommandArgument permission(String permission) {
        this.permission = permission;

        return this;
    }
}
