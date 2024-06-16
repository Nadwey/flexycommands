package pl.nadwey.flexycommands.argument;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuggestionResult {

	private List<String> suggestions;
	private String remaining;
	private boolean shouldContinue;
}
