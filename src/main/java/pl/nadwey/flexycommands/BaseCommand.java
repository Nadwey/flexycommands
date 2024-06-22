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


	@Getter
	private final String permission;

	@Getter
	private String permissionMessage;


	private BaseCommand(String prefix, String name, String permission) {
		this.prefix = prefix;
		this.name = name;
		this.permission = permission;
    }

	public static BaseCommand create(String prefix, String name, String  permission) {
		return new BaseCommand(prefix, name, permission);
	}

	public BaseCommand addArgument(BaseCommandArgument argument) {
		arguments.add(argument);

		return this;
	}



	public boolean  permissionExit() {
        return permission != null;
    }

    public BaseCommand addPermission(String permission, String permissionMessages) {
		permission = permission;
		permissionMessage = permissionMessages;
		return this;
	}

}
