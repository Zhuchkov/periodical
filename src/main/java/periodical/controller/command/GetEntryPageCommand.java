package periodical.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.EntryPageInput;
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
		EntryPageInput inputParams = extractInput(request);
		List<PeriodicalEntry> entries =  entryService.getLastAvailableEntries(inputParams);
		request.setAttribute("entries", entries);
		request.setAttribute("inputParams", inputParams);
		return Page.ENTRY_JSP;
		
	}

	private EntryPageInput extractInput(HttpServletRequest request) {
		User user = getUserFromRequest(request);
		int subscriptionId = Integer.valueOf(request.getParameter("subscriptionId"));
		String entryPageParam = request.getParameter("entryPage");
		int entryPage =(entryPageParam!=null?Integer.valueOf(entryPageParam):0); 
		return new EntryPageInput.Builder()
				.setUserId(user.getId())
				.setSubscriptionId(subscriptionId)
				.setEntryPage(entryPage)
				.build();
	}
	
}
