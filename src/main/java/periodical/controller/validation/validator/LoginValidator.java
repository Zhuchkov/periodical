package periodical.controller.validation.validator;

import periodical.controller.dto.LoginInput;
import periodical.controller.validation.rule.login.EmailRule;
import periodical.controller.validation.rule.login.LoginRule;
import periodical.controller.validation.rule.login.PasswordRule;


public class LoginValidator extends Validator<LoginRule, LoginInput> {

	private static class Holder{
		private static LoginValidator instance = new LoginValidator();
		static{
			instance.addRule(new EmailRule());
			instance.addRule(new PasswordRule());
		}
	}
	public static LoginValidator getInstance(){
		return Holder.instance;
	}
	LoginValidator(){
		
	}
}
