package periodical.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.model.service.SubscriptionService;

public class PaySubscriptionFeeCommand implements Command{
	SubscriptionService subscriptionService;
	public PaySubscriptionFeeCommand(SubscriptionService subscriptionService) {
		this.subscriptionService=subscriptionService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int subscriptionId = Integer.valueOf(request.getParameter("subscriptionId"));
		subscriptionService.paySubscriptionFee(subscriptionId);
		return executeCommand("getUserDetailsPage", request, response);
	}

}
