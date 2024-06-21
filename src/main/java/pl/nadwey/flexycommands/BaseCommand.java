package pl.nadwey.flexycommands;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import pl.nadwey.flexycommands.argument.BaseCommandArgument;
import pl.nadwey.flexycommands.argument.BaseCommandPermission;

public final class BaseCommand {

	@Getter
	private final String prefix;

	@Getter
	private final String name;

	@Getter
	private final List<BaseCommandArgument> arguments = new ArrayList<>();

	@Getter
	private final List<BaseCommandPermission> permissions = new ArrayList<>();

	@Getter
	private String permissionMessage;

	private BaseCommand(String prefix, String name, String permissionMessage) {
		this.prefix = prefix;
		this.name = name;
        this.permissionMessage = permissionMessage;
    }

	public static BaseCommand create(String prefix, String name) {
		return new BaseCommand(prefix, name, );
	}

	public BaseCommand addArgument(BaseCommandArgument argument) {
		arguments.add(argument);

		return this;
	}

    public BaseCommand addPermission(BaseCommandPermission permission, String permissionMessages) {
		permissions.add(permission);
		permissionMessage = permissionMessages;
		return this;
	}

}
