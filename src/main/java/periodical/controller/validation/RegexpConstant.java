package periodical.controller.validation;

import java.util.regex.Pattern;

public class RegexpConstant {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	private static final String PASSWORD_REGEX = "^[a-zA-Z]\\w{3,14}$";
	private static final String NAME_REGEX ="^[a-zA-Zа-яА-Я]{3,}$";
	private static final String CURRENCY_REGEX ="^\\d+(\\.\\d{2})?$";
	private static final String TEXT_REGEX ="^[a-zA-Zа-яА-Я\\s]*$";
	
	
	public static final Pattern EMAIL  = Pattern.compile(EMAIL_REGEX);
	public static final Pattern PASSWORD  = Pattern.compile(PASSWORD_REGEX);		
	public static final Pattern NAME  = Pattern.compile(NAME_REGEX);
	public static final Pattern CURRENCY  = Pattern.compile(CURRENCY_REGEX);
	public static final Pattern TEXT  = Pattern.compile(TEXT_REGEX);
			
}
