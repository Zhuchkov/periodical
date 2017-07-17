package periodical.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import periodical.model.entity.User;

@FunctionalInterface
public interface Command {
	String execute(HttpServletRequest request, HttpServletResponse response);

	 default User getUserFromRequest(HttpServletRequest request) {
		 HttpSession session = request.getSession();
		return (User) session.getAttribute("user");
	}

	 default String executeCommand(String commandName, HttpServletRequest request, HttpServletResponse response ){
		return CommandFactory.getInstance().getCommand(commandName).execute(request, response);
		
	 }
}
