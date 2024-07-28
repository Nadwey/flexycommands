package pl.nadwey.flexycommands.argument;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParseResult {
	/**
	 * Rest of the command string after the parsed argument
	 * without the space at the beginning
	 */
	private String remaining;
	/**
	 * Is the parsed value valid
 	 */
	private boolean valid;
	/**
	 * Should we continue and parse the next argument
	 */
	private boolean shouldContinue;
}
