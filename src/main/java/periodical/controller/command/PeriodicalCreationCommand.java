package periodical.controller.command;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.PeriodicalCreationInput;
import periodical.controller.validation.ValidationError;
import periodical.controller.validation.ValidatorFactory;
import periodical.controller.validation.validator.PeriodicalCreationValidator;
import periodical.model.entity.Periodical;
import periodical.model.entity.User;
import periodical.model.entity.UserDetails;
import periodical.model.service.PeriodicalService;

public class PeriodicalCreationCommand implements Command {

	private PeriodicalService periocalService;
	private ValidatorFactory validatorFactory;

	PeriodicalCreationCommand(PeriodicalService periocalService, ValidatorFactory validatorFactory) {
		this.periocalService = periocalService;
		this.validatorFactory = validatorFactory;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		PeriodicalCreationInput input = extractInput(request);
		PeriodicalCreationValidator validator = validatorFactory.createPeriodicalCreationValidator();
		List<ValidationError> errors = validator.validate(input);
		if (errors.isEmpty()) {
			Periodical periodical = createPeriodicalFromInput(request,input);
			int categoryId = Integer.valueOf(request.getParameter("category"));
			periocalService.create(periodical, categoryId);
			return executeCommand("getUserDetailsPage", request, response);
		}else{
			request.setAttribute("errors", errors);
			return executeCommand("getPeriodicalCreationPage", request, response);
		}
	}

	private PeriodicalCreationInput extractInput(HttpServletRequest request) {
		return new PeriodicalCreationInput.Builder().setName(request.getParameter("name"))
				.setCost(request.getParameter("cost")).build();
	}

	private Periodical createPeriodicalFromInput(HttpServletRequest request,PeriodicalCreationInput input) {
		User user = getUserFromRequest(request);
		UserDetails publisher = new UserDetails.Builder().setId(user.getId()).build();
		String name = input.getName();
		BigDecimal cost = new BigDecimal(input.getCost());
		return new Periodical.Builder().setName(name).setCost(cost).setPublisher(publisher).build();
	}

}
