package periodical.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import periodical.controller.dto.UserDetailsPagination;
import periodical.model.entity.User;
import periodical.model.entity.UserDetails;
import periodical.model.service.UserDetailsService;

public class GetUserDetailsPageCommand implements Command{

	
	UserDetailsService userDetailsService;
	GetUserDetailsPageCommand(UserDetailsService userDetailsService){
		this.userDetailsService=userDetailsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = getUserFromRequest(request);
		UserDetailsPagination paginationParams = extractPaginationParams(request);
		
		Optional<UserDetails> userDetails = userDetailsService.getUserDetailsWithPeriodicalsAndSubscriptions(user,paginationParams);
		if(userDetails.isPresent()){
			request.setAttribute("userDetails", userDetails.get()) ;
			request.setAttribute("paginationParams",paginationParams);
		}
		return Page.USER_DETAILS_JSP;
	}

	private UserDetailsPagination extractPaginationParams(HttpServletRequest request) {
		String subscriptionPageParam = request.getParameter("subscriptionPage");
		String periodicalPageParam = request.getParameter("periodicalPage");
		int subscriptionPage=(subscriptionPageParam!=null?Integer.valueOf(subscriptionPageParam):0);
		int periodicalPage=(periodicalPageParam!=null?Integer.valueOf(periodicalPageParam):0);
		return  new UserDetailsPagination.Builder()
				.setPeriodicalPage(periodicalPage)
				.setSubscriptionPage(subscriptionPage)
				.build();
	}

}
