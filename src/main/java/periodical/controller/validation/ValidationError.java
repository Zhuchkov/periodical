package periodical.controller.validation;

public enum ValidationError {
	EMAIL("email is incorrect"),
	PASSWORD("password is incorrect"),
	PASSWORD_EQUALITY("passwords not equal"),
	
	FIRST_NAME("first name incorrect"),
	LAST_NAME("last name incorrect"),
	
	PERIODICAL_NAME("periodical name incorrect"),
	PERIODICAL_COST("cost format incorrect"),
	
	ENTRY_NAME("entry name incorrect"),
	ENTRY_TEXT("entry text incorrect"),
	
	PUBLISHER_NAME("publisher name incorrect"),
	MAX_COST("max cost incorrect"),
	

	EMAIL_OCCUPIED("email occupied"),
	EMAIL_PASSWORD_MISMATCH("email and password combination not found"),
	EMAIL_NOT_FOUND("email not found"),
	
	PERIODICAL_OWNERSHIP("you do not own this periodical");
	
	
	private String message;
	private ValidationError(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}

}
