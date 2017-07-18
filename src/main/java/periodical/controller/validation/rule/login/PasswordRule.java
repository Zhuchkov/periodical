package periodical.controller.validation.rule.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.LoginInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class PasswordRule implements LoginRule {
	
	ValidationError error = ValidationError.PASSWORD;
	private Pattern passwordPattern = RegexpConstant.PASSWORD;
	
	@Override
	public boolean checkRule(LoginInput input) {
		String password = input.getPassword();
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
