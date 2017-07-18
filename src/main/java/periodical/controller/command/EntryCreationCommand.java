package periodical.controller.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.EntryCreationInput;
import periodical.controller.validation.ValidationError;
import periodical.controller.validation.ValidatorFactory;
import periodical.controller.validation.validator.EntryCreationValidator;
import periodical.model.entity.PeriodicalEntry;
import periodical.model.entity.User;
import periodical.model.service.EntryService;

public class EntryCreationCommand implements Command{

	private EntryService entryService;
	private ValidatorFactory validatorFactory;
	
	public EntryCreationCommand(EntryService entryService,ValidatorFactory validatorFactory) {
		this.entryService=entryService;
		this.validatorFactory=validatorFactory;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page;
		EntryCreationInput input = extractEntryParamsFromRequest(request);
		EntryCreationValidator validator = validatorFactory.createEntryCreationValidator();
		List<ValidationError> errors = validator.validate(input);
		if (errors.isEmpty()) {
			Optional<PeriodicalEntry> entry = entryService.saveEntryAndNotifySubscribers(input);
			if (entry.isPresent()) {
				page = executeCommand("getUserDetailsPage", request, response);
			} else {
				errors.add(ValidationError.PERIODICAL_OWNERSHIP);
				request.setAttribute("entryParams", input);
				page = executeCommand("getPeriodicalEntryCreationPage", request, response);
			}
		}else{
			page = executeCommand("getPeriodicalEntryCreationPage", request, response);
		}
		request.setAttribute("errors", errors);
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
