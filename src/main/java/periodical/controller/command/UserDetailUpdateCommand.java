package periodical.controller.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.User;
import periodical.model.entity.UserDetails;
import periodical.model.service.UserDetailsService;

public class UserDetailUpdateCommand implements Command{

	private UserDetailsService userDetailsService;
	public UserDetailUpdateCommand(UserDetailsService userDetailsService){
		this.userDetailsService=userDetailsService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = getUserFromRequest(request);
		String firstName = request.getParameter("first name");
		try {
			System.out.println(new String(firstName.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lastName = request.getParameter("last name");
		userDetailsService.updateUserDetails(user, firstName, lastName);
		
		return executeCommand("getUserDetailsPage", request, response);
	}

}
