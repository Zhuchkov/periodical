package periodical.controller.validation.rule.periodical_search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class PeriodicalNameRule implements PeriodicalSearchRule {

	private ValidationError error = ValidationError.PERIODICAL_NAME;
	private Pattern namePattern = RegexpConstant.TEXT;

	@Override
	public boolean checkRule(PeriodicalSearchParameters input) {
		boolean result;
		if (input.hasPeriodicalName()) {
			String periodicalName = input.getPublisherName();
			Matcher matcher = namePattern.matcher(periodicalName);
			result = matcher.matches();
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
