package periodical.controller.validation.rule.periodical_creation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import periodical.controller.dto.PeriodicalCreationInput;
import periodical.controller.validation.RegexpConstant;
import periodical.controller.validation.ValidationError;

public class PeriodicalNameRule implements PeriodicalCreationRule {

	private ValidationError error= ValidationError.PERIODICAL_NAME;
	private Pattern namePattern = RegexpConstant.TEXT;

	@Override
	public boolean checkRule(PeriodicalCreationInput input) {
		String name = input.getName();
		Matcher matcher = namePattern.matcher(name);
		return matcher.matches();
		
	}
	@Override
	public ValidationError getError() {
		// TODO Auto-generated method stub
		return error;
	}

}
