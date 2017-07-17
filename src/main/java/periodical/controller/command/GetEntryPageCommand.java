package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.PeriodicalEntry;
import periodical.model.entity.User;
import periodical.model.service.EntryService;

public class GetEntryPageCommand implements Command{

	EntryService entryService;

	public GetEntryPageCommand(EntryService entryService) {
		this.entryService = entryService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = getUserFromRequest(request);
		int subscriptionId = Integer.valueOf(request.getParameter("subscriptionId"));
		List<PeriodicalEntry> entries =  entryService.getLastAvailableEntries(user,subscriptionId);
		request.setAttribute("entries", entries);
		return "/WEB-INF/jsp/entry.jsp";
		
	}
	
}
