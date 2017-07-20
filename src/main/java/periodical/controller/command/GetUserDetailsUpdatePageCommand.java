package periodical.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.User;
import periodical.model.entity.UserDetails;
import periodical.model.service.UserDetailsService;

public class GetUserDetailsUpdatePageCommand implements Command {
	
	UserDetailsService userDetailsService;
	GetUserDetailsUpdatePageCommand(UserDetailsService userDetailsService){
		this.userDetailsService=userDetailsService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = "/index.jsp";
		User user = getUserFromRequest(request);
		Optional<UserDetails> userDetails = userDetailsService.getUserDetails(user);
		if(userDetails.isPresent()){
			request.setAttribute("userDetails", userDetails.get());
			page = Page.USER_DETAILS_UPDATE_JSP;
		}
		return page;
	}

}
