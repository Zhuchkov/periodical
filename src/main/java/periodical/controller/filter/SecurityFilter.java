package periodical.controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import periodical.model.entity.User;

public class SecurityFilter implements Filter {

	private static final String INDEX_PAGE = "/index.jsp";
	List<String> ignoreCommand;

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
		
		if (command!=null&&!ignoreCommand.contains(command)) {
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
	public void init(FilterConfig filterConfig) throws ServletException {
		ignoreCommand =Arrays.asList(filterConfig.getInitParameter("ignoreCommand").split(",")) ;
		if(ignoreCommand == null){
			ignoreCommand=new LinkedList<>();
		}
	}

}
