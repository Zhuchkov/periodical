package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.RegistrationInput;
import periodical.controller.validation.ValidationError;
import periodical.controller.validation.ValidatorFactory;
import periodical.controller.validation.rule.registration.RegistrationRule;
import periodical.controller.validation.validator.Validator;
import periodical.model.dao.exceptions.EmailOccupiedException;
import periodical.model.entity.User;
import periodical.model.service.UserService;

public class RegistrationCommand implements Command {

	private UserService userService;
	private ValidatorFactory validatorFactory;

	public RegistrationCommand(UserService userService, ValidatorFactory validatorFactory) {
		this.userService = userService;
		this.validatorFactory = validatorFactory;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		RegistrationInput input = extractInput(request);
		
		Validator<RegistrationRule, RegistrationInput> validator = validatorFactory.creareRegistrationValidator();

		List<ValidationError> errors = validator.validate(input);
		if (errors.isEmpty()) {
			try {
				User user = userService.saveUser(input);
				request.setAttribute("createdUser", user);
				page = "/login.jsp";
			} catch (EmailOccupiedException e) {
				System.out.println("catch");
				User user = e.getUser();
				request.setAttribute("email", user.getEmail());
				errors.add(ValidationError.EMAIL_OCCUPIED);
				request.setAttribute("errors", errors);
				page = "/registration.jsp";
			}
		} else {
			request.setAttribute("email", input.getEmail());
			request.setAttribute("errors", errors);
			request.setAttribute("emailError", ValidationError.EMAIL);
			request.setAttribute("passwordError", ValidationError.PASSWORD);
			request.setAttribute("passwordEqualityError", ValidationError.PASSWORD_EQUALITY);
			page = "/registration.jsp";
		}
		return page;
	}

	private RegistrationInput extractInput(HttpServletRequest request) {
		return new RegistrationInput.Builder().setEmail(request.getParameter("email"))
				.setFirstPassword(request.getParameter("password"))
				.setSecondPassword(request.getParameter("secondPassword")).build();
	}

}
