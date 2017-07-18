package periodical.controller.validation.rule.periodical_search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.PeriodicalSearchParameters;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class PublisherNameRule implements PeriodicalSearchRule{

	private ValidationError error = ValidationError.PUBLISHER_NAME;
	private Pattern namePattern = RegexpConstant.NAME;

	@Override
	public boolean checkRule(PeriodicalSearchParameters input) {
		boolean result;
		if (input.hasPublisherName()) {
			String publisherName = input.getPublisherName();
			Matcher matcher = namePattern.matcher(publisherName);
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
