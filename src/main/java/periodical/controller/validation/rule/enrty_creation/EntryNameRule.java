package periodical.controller.validation.rule.enrty_creation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.EntryCreationInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class EntryNameRule implements EntryCreationRule {
	
	private ValidationError error= ValidationError.ENTRY_NAME;
	private Pattern namePattern = RegexpConstant.TEXT;
	
	@Override
	public boolean checkRule(EntryCreationInput input) {
		String name = input.getEntryName();
		Matcher matcher = namePattern.matcher(name);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
