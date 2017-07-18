package periodical.controller.validation.validator;

import periodical.controller.dto.PeriodicalCreationInput;
import periodical.controller.validation.rule.periodical_creation.PeriodicalCostRule;
import periodical.controller.validation.rule.periodical_creation.PeriodicalCreationRule;
import periodical.controller.validation.rule.periodical_creation.PeriodicalNameRule;

public class PeriodicalCreationValidator extends Validator<PeriodicalCreationRule,PeriodicalCreationInput> {

	private static class Holder{
		private static PeriodicalCreationValidator instance = new PeriodicalCreationValidator();
		static{
			instance.addRule(new PeriodicalCostRule());
			instance.addRule(new PeriodicalNameRule());
		}
	}
	public static PeriodicalCreationValidator getInstance(){
		return Holder.instance;
	}
	PeriodicalCreationValidator(){
		
	}
}
