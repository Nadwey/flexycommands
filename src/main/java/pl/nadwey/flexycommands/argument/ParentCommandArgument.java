package pl.nadwey.flexycommands.argument;

import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ParentCommandArgument extends BaseCommandArgument {
    @Getter
    private final List<BaseCommandArgument> children = new ArrayList<>();

    protected ParentCommandArgument(String name) {
        super(name);
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public List<String> suggestChildren(String input) {
        List<String> suggestions = new ArrayList<>();

        for (BaseCommandArgument child : getChildren()) {
            SuggestionResult result = child.suggest(input);

            if (result.isShouldContinue()) {
                if (child instanceof ParentCommandArgument && child.hasChildren()) {
                    ParentCommandArgument parentChild = (ParentCommandArgument) child;

                    return parentChild.suggestChildren(result.getRemaining());
                }

                return Collections.emptyList();
            }

            suggestions.addAll(result.getSuggestions());
        }

        return suggestions;
    }

    public void parseChildren(CommandContext context, String input) {
        for (BaseCommandArgument child : children) {
            ParseResult result = child.parse(context, input);

            if (result.isShouldContinue()) {
                if (child instanceof ParentCommandArgument && child.hasChildren()) {
                    ParentCommandArgument parentChild = (ParentCommandArgument) child;

                    parentChild.parseChildren(context, result.getRemaining());
                }
            }
        }
    }

    public ParentCommandArgument then(BaseCommandArgument argument) {
        children.add(argument);

        return this;
    }
}
