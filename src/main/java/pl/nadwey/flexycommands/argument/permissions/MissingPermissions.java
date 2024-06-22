package pl.nadwey.flexycommands.argument.permissions;

import lombok.Getter;
import org.bukkit.command.CommandSender;


@Getter
public abstract class MissingPermissions {
    public static boolean check(CommandSender sender, String permission) {

        return sender.hasPermission(permission);
    }

    public abstract void handle(CommandSender sender, String permission, String permissionMessage);
}
