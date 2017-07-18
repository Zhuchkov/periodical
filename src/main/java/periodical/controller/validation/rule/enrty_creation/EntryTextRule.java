package periodical.controller.validation.rule.enrty_creation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.EntryCreationInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class EntryTextRule implements EntryCreationRule {
	
	private ValidationError error= ValidationError.ENTRY_TEXT;
	private Pattern namePattern = RegexpConstant.TEXT;
	
	@Override
	public boolean checkRule(EntryCreationInput input) {
		String text = input.getEntryName();
		Matcher matcher = namePattern.matcher(text);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}
}