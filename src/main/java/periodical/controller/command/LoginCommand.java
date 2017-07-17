package periodical.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import periodical.model.entity.User;
import periodical.model.service.UserService;

public class LoginCommand implements Command{

	private UserService userService;
	public LoginCommand(UserService userService){
		this.userService=userService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = "";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Optional<User> user = userService.findUser(email);
		if(user.isPresent()){
			if(userService.checkPassword(user.get(), password)){
				HttpSession session = request.getSession();
				session.setAttribute("user", user.get());
				page="/WEB-INF/jsp/main.jsp";
			}else{
				request.setAttribute("wrongPassword", "Wrong password");
				request.setAttribute("email",email);
				page = "/login.jsp";
			}
		}else{
			request.setAttribute("userNotFound", "User not found");
			page = "/login.jsp";
		}
		return page;
		
	}

}
