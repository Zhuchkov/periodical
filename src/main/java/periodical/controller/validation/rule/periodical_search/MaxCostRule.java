package periodical.controller.validation.rule.periodical_search;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class MaxCostRule implements PeriodicalSearchRule {

	private ValidationError error = ValidationError.PERIODICAL_COST;
	private Pattern currencyPattern = RegexpConstant.CURRENCY;

	@Override
	public boolean checkRule(PeriodicalSearchParameters input) {
		boolean result = false;
		if (input.hasMaxCost()) {
			String maxCost = input.getMaxCost();
			Matcher matcher = currencyPattern.matcher(maxCost);
			if(matcher.matches()){
				BigDecimal numericCost =new BigDecimal(maxCost);
				if(numericCost.compareTo(BigDecimal.ZERO)>=0){
					result =true;
				}
			}
		}else{
			result = true;
		}
		return result;
	}
	@Override
	public ValidationError getError() {
		return error;
	}
}
