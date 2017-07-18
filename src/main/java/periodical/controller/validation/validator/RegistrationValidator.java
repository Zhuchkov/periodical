package periodical.controller.validation.validator;

import periodical.controller.dto.RegistrationInput;
import periodical.controller.validation.rule.registration.EmailRule;
import periodical.controller.validation.rule.registration.PasswordEqualityRule;
import periodical.controller.validation.rule.registration.PasswordRule;
import periodical.controller.validation.rule.registration.RegistrationRule;

public class RegistrationValidator extends Validator<RegistrationRule,RegistrationInput>{

	private static class Holder{
		private static RegistrationValidator instance = new RegistrationValidator();
		static{
			instance.addRule(new EmailRule());
			instance.addRule(new PasswordRule());
			instance.addRule(new PasswordEqualityRule());
		}
	}
	public static RegistrationValidator getInstance(){
		return Holder.instance;
	}
	RegistrationValidator(){
		
	}
}
