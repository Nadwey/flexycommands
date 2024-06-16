package pl.nadwey.flexycommands.utility;

import org.bukkit.permissions.Permission;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class TextUtils {
    public static List<String> filterStartingWith(final Collection<String> list, final String prefix) {
        return list
                .stream()
                .filter(string -> string.startsWith(prefix))
                .collect(Collectors.toList());
    }
}
