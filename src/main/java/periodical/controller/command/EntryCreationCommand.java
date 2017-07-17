package periodical.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.EntryCreationInput;
import periodical.model.entity.PeriodicalEntry;
import periodical.model.entity.User;
import periodical.model.service.EntryService;

public class EntryCreationCommand implements Command{

	EntryService entryService;
	
	public EntryCreationCommand(EntryService entryService) {
		this.entryService=entryService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page;
		EntryCreationInput entryParams = extractEntryParamsFromRequest(request);
		Optional<PeriodicalEntry> entry =  entryService.saveEntryAndNotifySubscribers(entryParams);
		if(entry.isPresent()){
			request.setAttribute("entryParams", entryParams);
			page=executeCommand("getPeriodicalEntryCreationPage", request, response);
			System.out.println("true");
		}else{
			request.setAttribute("entryParams", entryParams);
			page=executeCommand("getPeriodicalEntryCreationPage", request, response);
			System.out.println("false");
		}
		return page;
	}

	private EntryCreationInput extractEntryParamsFromRequest(HttpServletRequest request) {
		int periodicalId = Integer.valueOf(request.getParameter("periodicalId"));
		String entryName = request.getParameter("entryName");
		String entryText = request.getParameter("entryText");
		User requestCreator = getUserFromRequest(request);
		return new EntryCreationInput.Builder()
				.setEntryName(entryName)
				.setEntryText(entryText)
				.setPeriodicalId(periodicalId)
				.setRequestCreator(requestCreator)
				.build();
	}

}
