package pl.nadwey.flexycommands.argument;

/**
 * Does not get added to context
 */
public final class VirtualParseResult extends ParseResult {

	public VirtualParseResult(
		Object value,
		String remaining,
		boolean valid,
		boolean shouldContinue
	) {
		super(value, remaining, valid, shouldContinue);
	}
}
