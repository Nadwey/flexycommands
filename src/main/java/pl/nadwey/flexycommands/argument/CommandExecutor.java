package pl.nadwey.flexycommands.argument;

import pl.nadwey.flexycommands.CommandContext;

@FunctionalInterface
public interface CommandExecutor {
    void execute(CommandContext context);
}
