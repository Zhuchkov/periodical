package periodical.controller.validation.rule.user_update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.UserDetailsInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class LastNameRule implements UserDetailsUpdateRule {

	private ValidationError error = ValidationError.LAST_NAME;
	private Pattern namePattern = RegexpConstant.NAME;

	@Override
	public boolean checkRule(UserDetailsInput input) {
		String lastName = input.getLastName();
		Matcher matcher = namePattern.matcher(lastName);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
