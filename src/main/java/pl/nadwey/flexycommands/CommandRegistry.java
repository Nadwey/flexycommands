package pl.nadwey.flexycommands;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public class CommandRegistry {

	private final CommandMap commandMap;

	private CommandRegistry(CommandMap commandMap) {
		this.commandMap = commandMap;
	}

	public static CommandRegistry create(Server server) {
		CommandMap commandMap = null;
		try {
			commandMap = (CommandMap) server
				.getClass()
				.getDeclaredMethod("getCommandMap")
				.invoke(server);
		} catch (
			IllegalAccessException | InvocationTargetException | NoSuchMethodException e
		) {
			try {
				Field commandMapField = server
					.getPluginManager()
					.getClass()
					.getField("commandMap");

				commandMapField.setAccessible(true);
				commandMap = (CommandMap) commandMapField.get(server);
			} catch (NoSuchFieldException | IllegalAccessException ex) {
				throw new RuntimeException(ex);
			}
		}

		return new CommandRegistry(commandMap);
	}

	/**
	 * Registers a command. Returns true on success; false if name is already taken and fallback had to be used.
	 *
	 * @param label          the label of the command, without the '/'-prefix.
	 * @param fallbackPrefix a prefix which is prepended to the command with a ':' one or more times to make the command unique
	 * @param command        the command to register
	 */
	private void register(String label, String fallbackPrefix, Command command) {
		commandMap.register(label, fallbackPrefix, command);
	}

	public void register(BaseCommand command) {
		register(command.getName(), command.getPrefix(), new BukkitCommand(command));
	}
}
