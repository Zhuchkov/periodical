package periodical.controller.validation.rule;

import periodical.controller.validation.ValidationError;

public interface ValidationRule<E> {
	boolean checkRule(E input);
	ValidationError getError();
}
