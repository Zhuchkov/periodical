package periodical.controller.validation.validator;

import periodical.controller.dto.EntryCreationInput;
import periodical.controller.validation.rule.enrty_creation.EntryCreationRule;
import periodical.controller.validation.rule.enrty_creation.EntryNameRule;
import periodical.controller.validation.rule.enrty_creation.EntryTextRule;

public class EntryCreationValidator extends Validator<EntryCreationRule,EntryCreationInput> {

	private static class Holder{
		private static EntryCreationValidator instance = new EntryCreationValidator();
		static{
			instance.addRule(new EntryNameRule());
			instance.addRule(new EntryTextRule());
		}
	}
	public static EntryCreationValidator getInstance(){
		return Holder.instance;
	}
	EntryCreationValidator(){
		
	}
}