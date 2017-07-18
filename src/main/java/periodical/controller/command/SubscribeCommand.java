package periodical.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.entity.User;
import periodical.model.service.SubscriptionService;

public class SubscribeCommand implements Command {
	SubscriptionService subscriptionService;

	public SubscribeCommand(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int periodicalId = Integer.valueOf(request.getParameter("periodicalId"));
		User user = getUserFromRequest(request);
		subscriptionService.subscribeUser(user,periodicalId);
		return executeCommand("getUserDetailsPage", request, response);
	}

}
