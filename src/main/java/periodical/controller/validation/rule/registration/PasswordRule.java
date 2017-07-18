package periodical.controller.validation.rule.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.RegistrationInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class PasswordRule implements RegistrationRule {
	ValidationError error = ValidationError.PASSWORD;
	private Pattern passwordPattern = RegexpConstant.PASSWORD;
	
	@Override
	public boolean checkRule(RegistrationInput input) {
		String password = input.getFirstPassword();
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
