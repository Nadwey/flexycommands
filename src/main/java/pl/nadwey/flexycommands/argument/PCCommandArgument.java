package pl.nadwey.flexycommands.argument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import pl.nadwey.flexycommands.CommandContext;

public abstract class PCCommandArgument {

	@Getter
	private final String name;

	@Getter
	private final List<PCCommandArgument> children = new ArrayList<>();

	public PCCommandArgument(String name) {
		this.name = name;
	}

	public abstract ParseResult parse(String input);

	public abstract SuggestionResult suggest(String input);

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public List<String> suggestChildren(String input) {
		List<String> suggestions = new ArrayList<>();

		for (PCCommandArgument argument : getChildren()) {
			SuggestionResult result = argument.suggest(input);

			if (result.isShouldContinue()) {
				if (argument.hasChildren()) {
					return argument.suggestChildren(result.getRemaining());
				}

				return Collections.emptyList();
			}

			suggestions.addAll(result.getSuggestion());
		}

		return suggestions;
	}

	public ParseResult parse(CommandContext context, String input) {
		ParseResult result = parse(input);

		if (!(result instanceof VirtualParseResult)) context.set(
			getName(),
			result.getValue()
		);

		return result;
	}

	public void parseChildren(CommandContext context, String input) {
		for (PCCommandArgument argument : getChildren()) {
			ParseResult result = argument.parse(context, input);

			if (result.isShouldContinue()) {
				if (argument.hasChildren()) {
					argument.parseChildren(context, result.getRemaining());
				}
			}
		}
	}

	public PCCommandArgument then(PCCommandArgument argument) {
		children.add(argument);

		return this;
	}
}
