package pl.nadwey.flexycommands.argument;

import pl.nadwey.flexycommands.ChildrenHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class ParentCommandArgument extends BaseCommandArgument implements ChildrenHolder {
    private final List<BaseCommandArgument> children = new ArrayList<>();

    protected ParentCommandArgument(String name) {
        super(name);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public ParentCommandArgument then(BaseCommandArgument argument) {
        children.add(argument);

        return this;
    }

    @Override
    public List<BaseCommandArgument> getChildren() {
        return children;
    }

    // allows us to use .then after .permission
    // yeah, I also don't like this "solution"
    @Override
    public ParentCommandArgument permission(String permission) {
        super.permission(permission);

        return this;
    }
}
