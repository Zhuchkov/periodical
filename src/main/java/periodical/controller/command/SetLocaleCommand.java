package periodical.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocaleCommand implements Command {

	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String locale = request.getParameter("language");
		HttpSession session = request.getSession();
		session.setAttribute("locale", locale);
		String page =Page.INDEX_JSP;
//		String previousCommand = request.getParameter("previousCommand");
//		if(previousCommand!=null&&(previousCommand.startsWith("get"))){
//			page = executeCommand(previousCommand, request, response);
//		}else{
//			page = "/index.jsp";
//		}
		return page;
	}

}
