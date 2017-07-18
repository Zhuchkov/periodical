package periodical.controller.validation.rule.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.LoginInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class EmailRule implements LoginRule{
	
	private ValidationError error= ValidationError.EMAIL;
	private Pattern emailPattern = RegexpConstant.EMAIL;
	
	@Override
	public boolean checkRule(LoginInput input) {
		String email = input.getEmail();
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		return error;
	}

}
