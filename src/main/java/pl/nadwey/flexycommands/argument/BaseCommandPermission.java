package pl.nadwey.flexycommands.argument;

import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;

@Getter
public abstract class BaseCommandPermission  {
    private final String name;

    private CommandExecutor executor;

    private final String permissionMessage;

    protected BaseCommandPermission(String name, String permissionMessage) {
        this.name = name;
        this.permissionMessage = permissionMessage;
    }


    public BaseCommandPermission execute(CommandExecutor executor) {
        this.executor = executor;

        return this;
    }
}
