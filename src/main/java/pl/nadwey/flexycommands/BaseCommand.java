package pl.nadwey.flexycommands;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import pl.nadwey.flexycommands.argument.BaseCommandArgument;

public final class BaseCommand {

	@Getter
	private final String prefix;

	@Getter
	private final String name;

	@Getter
	private final List<BaseCommandArgument> arguments = new ArrayList<>();

	private BaseCommand(String prefix, String name) {
		this.prefix = prefix;
		this.name = name;
	}

	public static BaseCommand create(String prefix, String name) {
		return new BaseCommand(prefix, name);
	}

	public BaseCommand addArgument(BaseCommandArgument argument) {
		arguments.add(argument);

		return this;
	}
}
