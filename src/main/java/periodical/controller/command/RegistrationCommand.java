package periodical.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.User;
import periodical.model.service.UserService;

public class RegistrationCommand implements Command{

	private UserService userService;
	public RegistrationCommand(UserService userService){
		this.userService=userService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repeatedPassword = request.getParameter("password");
		if(validate(email,password)){
			User user = userService.saveUser(email, password);
			request.setAttribute("createdUser", user);
			 page = "/login.jsp";
		}else{
			page = "/registration.jsp";
		}
		return page;
	}
	private boolean validate(String email, String password) {
		// TODO Auto-generated method stub
		return true;
	}

}
