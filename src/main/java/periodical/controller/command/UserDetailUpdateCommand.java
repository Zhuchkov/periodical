package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.UserDetailsInput;
import periodical.controller.validation.ValidationError;
import periodical.controller.validation.ValidatorFactory;
import periodical.controller.validation.validator.UserDetailsUpdateValidator;
import periodical.model.entity.User;
import periodical.model.service.UserDetailsService;

public class UserDetailUpdateCommand implements Command{

	private UserDetailsService userDetailsService;
	private ValidatorFactory validatorFactory;
	public UserDetailUpdateCommand(UserDetailsService userDetailsService,ValidatorFactory validatorFactory){
		this.userDetailsService=userDetailsService;
		this.validatorFactory=validatorFactory;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = getUserFromRequest(request);
		UserDetailsInput input = extractInput(request);
		UserDetailsUpdateValidator validator = validatorFactory.createUserDetailsUpdateValidator();
		List<ValidationError> errors = validator.validate(input);
		if(errors.isEmpty()){
			userDetailsService.updateUserDetails(user, input);
			return executeCommand("getUserDetailsPage", request, response);
		}else{
			request.setAttribute("errors", errors);
			return executeCommand("getUserDetailsUpdatePage", request, response);
		}
	}
	private UserDetailsInput extractInput(HttpServletRequest request) {
		return new UserDetailsInput.Builder()
				.setFirstName( request.getParameter("firstName"))
				.setLastName(request.getParameter("lastName"))
				.build();
	}

}
