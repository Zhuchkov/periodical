package periodical.controller.validation.validator;

import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.controller.validation.rule.periodical_search.MaxCostRule;
import periodical.controller.validation.rule.periodical_search.PeriodicalNameRule;
import periodical.controller.validation.rule.periodical_search.PeriodicalSearchRule;
import periodical.controller.validation.rule.periodical_search.PublisherNameRule;

public class PeriodicalSearchValidator extends Validator<PeriodicalSearchRule, PeriodicalSearchParameters>{

	private static class Holder{
		private static PeriodicalSearchValidator instance = new PeriodicalSearchValidator();
		static{
			instance.addRule(new PeriodicalNameRule());
			instance.addRule(new PublisherNameRule());
			instance.addRule(new MaxCostRule());
		}
	}
	public static PeriodicalSearchValidator getInstance(){
		return Holder.instance;
	}
	PeriodicalSearchValidator(){
		
	}
}
