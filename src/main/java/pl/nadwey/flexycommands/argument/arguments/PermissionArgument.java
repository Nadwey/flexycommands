package pl.nadwey.flexycommands.argument.arguments;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.nadwey.flexycommands.SuggestionContext;
import pl.nadwey.flexycommands.argument.SuggestionResult;

public class PermissionArgument extends StringArgument {

	private final PluginManager pluginManager;

	public PermissionArgument(String name, PluginManager pluginManager) {
		super(name);
		this.pluginManager = pluginManager;
	}

	@Override
	public SuggestionResult suggest(SuggestionContext context, String value) {
		StringArgumentParseResult result = parse(value);

		List<String> permissions = pluginManager
			.getPermissions()
			.stream()
			.map(Permission::getName)
			.filter(permission -> permission.startsWith(result.value))
			.collect(Collectors.toList());

		return new SuggestionResult(
			permissions,
			result.remaining,
			result.shouldContinue
		);
	}
}
