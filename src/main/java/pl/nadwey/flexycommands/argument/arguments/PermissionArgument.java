package pl.nadwey.flexycommands.argument.arguments;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import pl.nadwey.flexycommands.argument.ParseResult;
import pl.nadwey.flexycommands.argument.SuggestionResult;

public class PermissionArgument extends StringArgument {

	private final JavaPlugin plugin;

	public PermissionArgument(String name, JavaPlugin plugin) {
		super(name);
		this.plugin = plugin;
	}

	@Override
	public SuggestionResult suggest(String value) {
		ParseResult result = parse(value);

		List<String> permissions = plugin
			.getServer()
			.getPluginManager()
			.getPermissions()
			.stream()
			.map(Permission::getName)
			.filter(permission -> permission.startsWith((String) result.getValue()))
			.collect(Collectors.toList());
		return new SuggestionResult(
			permissions,
			result.getRemaining(),
			result.isShouldContinue()
		);
	}
}
