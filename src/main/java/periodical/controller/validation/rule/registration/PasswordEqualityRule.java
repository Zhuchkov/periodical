package periodical.controller.validation.rule.registration;

import periodical.controller.dto.RegistrationInput;
import periodical.controller.validation.ValidationError;

public class PasswordEqualityRule implements RegistrationRule{

	@Override
	public boolean checkRule(RegistrationInput input) {
		String firstPassword = input.getFirstPassword();
		String secondPassword = input.getSecondPassword();
		return firstPassword.equals(secondPassword);
	}

	@Override
	public ValidationError getError() {
		// TODO Auto-generated method stub
		return ValidationError.PASSWORD_EQUALITY;
	}


	
}
