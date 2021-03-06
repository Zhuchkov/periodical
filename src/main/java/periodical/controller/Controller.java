package periodical.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.command.Command;
import periodical.controller.command.CommandFactory;

/**
 * 
 * @author Vladyslav_Zhuchkov
 *
 */
public class Controller extends HttpServlet {
	
	/**
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	/**
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	/**
	 * 
	 * @param req
	 * @param resp
<<<<<<< HEAD
	 * @throws ServletException
=======
	 * @throws Exception
>>>>>>> branch 'master' of https://github.com/Zhuchkov/periodical.git
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String page;
		CommandFactory commandFactory = CommandFactory.getInstance();
		Command command = commandFactory.defineCommand(req);
		try{
			page = command.execute(req, resp);
		}catch(Throwable e){
			page = "/WEB-INF/jsp/error/error.jsp";
		}
		if(page != null){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(req, resp);
		}
	}

}
