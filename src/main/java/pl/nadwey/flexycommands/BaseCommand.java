package pl.nadwey.flexycommands;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import pl.nadwey.flexycommands.argument.BaseCommandArgument;

public final class BaseCommand implements ChildrenHolder {
    private final List<BaseCommandArgument> children = new ArrayList<>();

    @Getter
    private final String prefix;

    @Getter
    private final String name;
    
    @Getter
    private String permission;

    private BaseCommand(String prefix, String name, String permission) {
        this.prefix = prefix;
        this.name = name;
        this.permission = permission;
    }

    private BaseCommand(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public static BaseCommand create(String prefix, String name) {
        return new BaseCommand(prefix, name);
    }

    public BaseCommand addArgument(BaseCommandArgument argument) {
        children.add(argument);

        return this;
    }

    public BaseCommand permission(String permission) {
        this.permission = permission;

        return this;
    }

    @Override
    public List<BaseCommandArgument> getChildren() {
        return children;
    }
}
