package periodical.controller.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import periodical.controller.dto.LoginInput;
import periodical.controller.validation.ValidationError;
import periodical.controller.validation.ValidatorFactory;
import periodical.controller.validation.validator.LoginValidator;
import periodical.model.entity.User;
import periodical.model.service.UserService;

public class LoginCommand implements Command{
	private ValidatorFactory validatorFactory;
	private UserService userService;
	public LoginCommand(UserService userService, ValidatorFactory validatorFactory){
		this.userService=userService;
		this.validatorFactory=validatorFactory;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page;
		LoginInput input = extractInput(request);
		
		LoginValidator validator = validatorFactory.createLoginValidator();
		List<ValidationError> errors = validator.validate(input);
		if(errors.isEmpty()){
		page = modelValidation(request, input,errors);
		}else{
			page=Page.LOGIN_JSP;
			request.setAttribute("emailError", ValidationError.EMAIL);
			request.setAttribute("passwordError", ValidationError.PASSWORD);
		}
		request.setAttribute("errors", errors);
		return page;
		
	}
	private String modelValidation(HttpServletRequest request, LoginInput input, List<ValidationError> errors) {
		String page;
		Optional<User> user = userService.findUser(input.getEmail());
		if(user.isPresent()){
			if(userService.checkPassword(user.get(), input.getPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("user", user.get());
				page=Page.INDEX_JSP;
			}else{
				errors.add(ValidationError.EMAIL_PASSWORD_MISMATCH);
				request.setAttribute("email",input.getEmail());
				page = Page.LOGIN_JSP;
			}
		}else{
			errors.add(ValidationError.EMAIL_NOT_FOUND);
			page = Page.LOGIN_JSP;
		}
		return page;
	}
	private LoginInput extractInput(HttpServletRequest request) {
		return new LoginInput.Builder()
				.setEmail(request.getParameter("email"))
				.setPassword(request.getParameter("password"))
				.build();
	}

}
