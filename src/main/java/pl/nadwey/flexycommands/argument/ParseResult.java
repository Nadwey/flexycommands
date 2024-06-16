package pl.nadwey.flexycommands.argument;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParseResult {

	private Object value;
	private String remaining;
	private boolean valid;
	private boolean shouldContinue;
}
