package periodical.controller.validation.rule.periodical_creation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.PeriodicalCreationInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class PeriodicalCostRule implements PeriodicalCreationRule {
	private ValidationError error = ValidationError.PERIODICAL_COST;
	private Pattern currencyPattern = RegexpConstant.CURRENCY;

	@Override
	public boolean checkRule(PeriodicalCreationInput input) {
		String cost = input.getCost();
		Matcher matcher = currencyPattern.matcher(cost);
		return matcher.matches();
	}

	@Override
	public ValidationError getError() {
		// TODO Auto-generated method stub
		return error;
	}

}
