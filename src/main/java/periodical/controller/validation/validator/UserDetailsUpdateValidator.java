package periodical.controller.validation.validator;

import periodical.controller.dto.UserDetailsInput;
import periodical.controller.validation.rule.user_update.FirstNameRule;
import periodical.controller.validation.rule.user_update.LastNameRule;
import periodical.controller.validation.rule.user_update.UserDetailsUpdateRule;

public class UserDetailsUpdateValidator extends Validator<UserDetailsUpdateRule, UserDetailsInput> {
	private static class Holder{
		private static UserDetailsUpdateValidator instance = new UserDetailsUpdateValidator();
		static{
			instance.addRule(new LastNameRule());
			instance.addRule(new FirstNameRule());
			
		}
	}
	public static UserDetailsUpdateValidator getInstance(){
		return Holder.instance;
	}
	UserDetailsUpdateValidator(){
		
	}
}
