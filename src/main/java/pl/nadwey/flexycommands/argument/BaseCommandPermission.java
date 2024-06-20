package pl.nadwey.flexycommands.argument;

import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;

@Getter
public abstract class BaseCommandPermission  {
    private final String name;

    private CommandExecutor executor;

    protected BaseCommandPermission(String name) {
        this.name = name;
    }

    public BaseCommandPermission execute(CommandExecutor executor) {
        this.executor = executor;

        return this;
    }
}
