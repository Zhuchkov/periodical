package periodical.controller.validation.rule.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.RegistrationInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class EmailRule implements RegistrationRule{
	
	private ValidationError error= ValidationError.EMAIL;
	private Pattern emailPattern = RegexpConstant.EMAIL;

	@Override
	public boolean checkRule(RegistrationInput input) {
		String email = input.getEmail();
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
		
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
