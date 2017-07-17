package periodical.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.Periodical;
import periodical.model.entity.User;
import periodical.model.service.PeriodicalService;

public class GetPeriodicalEntryCreationPageCommand implements Command {

	PeriodicalService periodicalService;
	public GetPeriodicalEntryCreationPageCommand(PeriodicalService periodicalService) {
		this.periodicalService=periodicalService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page;
		int periodicalId = Integer.valueOf(request.getParameter("periodicalId"));
		User currentUser = getUserFromRequest(request);
		Optional<Periodical> periodical = periodicalService.findPeriodicalByUserAndId(currentUser,periodicalId);
		if(periodical.isPresent()){
			request.setAttribute("periodical", periodical.get());
			page = "/WEB-INF/jsp/entryCreation.jsp";
		}else{
			request.setAttribute("errorMessage", "no periodical with such id was found");
			page = executeCommand("getUserDetailsPage", request, response);
		}
		return page;
	}

}
