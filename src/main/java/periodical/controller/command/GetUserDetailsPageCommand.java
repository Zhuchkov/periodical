package periodical.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.User;
import periodical.model.entity.UserDetails;
import periodical.model.service.UserDetailsService;

public class GetUserDetailsPageCommand implements Command{

	UserDetailsService userDetailsService;
	GetUserDetailsPageCommand(UserDetailsService userDetailsService){
		this.userDetailsService=userDetailsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = getUserFromRequest(request);
		Optional<UserDetails> userDetails = userDetailsService.getUserDetailsWithPeriodicalsAndSubscriptions(user);
		if(userDetails.isPresent()){
			request.setAttribute("userDetails", userDetails.get()) ;
		}
		return "/WEB-INF/jsp/userDetails.jsp";
	}

}
