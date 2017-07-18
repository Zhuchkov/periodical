package periodical.controller.validation;

import periodical.controller.validation.validator.EntryCreationValidator;
import periodical.controller.validation.validator.LoginValidator;
import periodical.controller.validation.validator.PeriodicalCreationValidator;
import periodical.controller.validation.validator.PeriodicalSearchValidator;
import periodical.controller.validation.validator.RegistrationValidator;
import periodical.controller.validation.validator.UserDetailsUpdateValidator;

public class ValidatorFactory {

	private static class Holder{
		private static ValidatorFactory instance = new ValidatorFactory();
	}
	
	public static ValidatorFactory getInstance(){
		return Holder.instance;
	}
	ValidatorFactory(){
		
	}
	
	public RegistrationValidator creareRegistrationValidator(){
		return RegistrationValidator.getInstance();
		
	}
	public LoginValidator createLoginValidator(){
		return LoginValidator.getInstance();
	}
	public UserDetailsUpdateValidator createUserDetailsUpdateValidator(){
		return UserDetailsUpdateValidator.getInstance();
	}
	public PeriodicalCreationValidator createPeriodicalCreationValidator(){
		return PeriodicalCreationValidator.getInstance();
	}
	public EntryCreationValidator createEntryCreationValidator(){
		return EntryCreationValidator.getInstance();
	}
	
	public PeriodicalSearchValidator createPeriodicalSearchValidator(){
		return PeriodicalSearchValidator.getInstance();
	}
}
