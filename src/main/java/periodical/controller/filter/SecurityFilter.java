package periodical.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import periodical.controller.command.Command;
import periodical.controller.command.CommandFactory;
import periodical.model.entity.User;

public class SecurityFilter implements Filter {

	private static final String INDEX_PAGE = "/index.jsp";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponce = (HttpServletResponse) response;
		String command = (String) request.getParameter("command");
		
		if (command!=null&&!command.equals("login")&&!command.equals("registration")&&!command.equals( "getPeriodicalsSearchPage")) {
			HttpSession session = httpRequest.getSession();
			
			User user = (User) session.getAttribute("user");
			if (user == null) {
				httpRequest.getServletContext().getRequestDispatcher(INDEX_PAGE).forward(httpRequest, httpResponce);
				return;
			}
		}
		filterChain.doFilter(httpRequest, httpResponce);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
