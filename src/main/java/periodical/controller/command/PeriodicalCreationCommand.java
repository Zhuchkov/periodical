package periodical.controller.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.Periodical;
import periodical.model.entity.User;
import periodical.model.entity.UserDetails;
import periodical.model.service.PeriodicalService;

public class PeriodicalCreationCommand implements Command {

	private PeriodicalService periocalService;

	PeriodicalCreationCommand(PeriodicalService periocalService) {
		this.periocalService = periocalService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Periodical periodical = extractPeriodicalFromRequest(request);
		int categoryId = Integer.valueOf(request.getParameter("category"));
		periocalService.create(periodical,categoryId);
		return CommandFactory.getInstance().getCommand("getUserDetailsPage").execute(request, response);
	}

	private Periodical extractPeriodicalFromRequest(HttpServletRequest request) {
		User user = getUserFromRequest(request);
		String name = request.getParameter("name");
		BigDecimal cost = new BigDecimal(request.getParameter("cost"));
		UserDetails publisher = new UserDetails.Builder().setId(user.getId()).build();
		return new Periodical.Builder()
				.setName(name)
				.setCost(cost)
				.setPublisher(publisher)
				.build();
	}

}
