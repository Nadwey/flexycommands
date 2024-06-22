package pl.nadwey.flexycommands.argument.permissions;

import org.bukkit.command.CommandSender;

public class InvalidPermissionHandler extends MissingPermissions {

        public boolean handle(CommandSender sender, String permission, String permissionMessage) {
            if (!check(sender, permission)) {
                sender.sendMessage(permissionMessage);
                return false;
            }
            return true;
        }
}
