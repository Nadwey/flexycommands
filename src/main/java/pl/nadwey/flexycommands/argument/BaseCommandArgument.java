package pl.nadwey.flexycommands.argument;

import lombok.AccessLevel;
import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseCommandArgument {
    @Getter
    private final String name;

    @Getter
    private CommandExecutor executor;

    protected BaseCommandArgument(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    protected BaseCommandArgument(String name) {
        this.name = name;
    }

    public abstract ParseResult parse(CommandContext context, String input);

    public abstract SuggestionResult suggest(String input);

    public abstract boolean hasChildren();

    @Getter
    private  String permission;

    @Getter
    public String permissionMessage;

    public BaseCommandArgument execute(CommandExecutor executor) {
        this.executor = executor;

        return this;
    }

    public boolean  permissionExit() {
        return permission != null;
    }

    public BaseCommandArgument addPermission(String permission, String permissionMessages) {
        permission = permission;
        permissionMessage = permissionMessages;
        return this;
    }
}
