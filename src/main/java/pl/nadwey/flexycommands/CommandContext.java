package pl.nadwey.flexycommands;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

public class CommandContext {

	private final Map<String, Object> values = new HashMap<>();

	public void set(String key, Object value) {
		values.put(key, value);
	}

	public Object get(String key) {
		return values.get(key);
	}

	public Map<String, Object> getMap() {
		return ImmutableMap.copyOf(values);
	}
}
