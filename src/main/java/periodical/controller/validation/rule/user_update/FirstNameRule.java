package periodical.controller.validation.rule.user_update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.UserDetailsInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class FirstNameRule implements UserDetailsUpdateRule {

	private ValidationError error = ValidationError.FIRST_NAME;
	private Pattern namePattern = RegexpConstant.NAME;

	@Override
	public boolean checkRule(UserDetailsInput input) {
		String firstName = input.getFirstName();
		Matcher matcher = namePattern.matcher(firstName);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
