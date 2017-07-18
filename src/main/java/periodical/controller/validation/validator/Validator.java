package periodical.controller.validation.validator;

import java.util.LinkedList;
import java.util.List;

import periodical.controller.validation.ValidationError;
import periodical.controller.validation.rule.ValidationRule;

public abstract class Validator<R extends ValidationRule<E>,E> {

	private List<R> rules = new LinkedList<>();

	public List<ValidationError> validate(E inputEntity) {
		List<ValidationError> errors = new LinkedList<>();
		for(R rule:rules){
			if(!rule.checkRule(inputEntity)){
				errors.add(rule.getError());
			}
		}
		return errors;
	}

	void addRule(R rule) {
		rules.add(rule);
	} 
	


	
}
