package periodical.controller.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import periodical.model.service.CategoryService;
import periodical.model.service.EntryService;
import periodical.model.service.PeriodicalService;
import periodical.model.service.SubscriptionService;
import periodical.model.service.UserDetailsService;
import periodical.model.service.UserService;

public class CommandFactory {

	private static class Holder {
		private static CommandFactory INSTANCE = new CommandFactory();
	}

	public static CommandFactory getInstance() {
		return Holder.INSTANCE;
	}

	CommandFactory() {

	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private Map<String, Command> commands = new HashMap() {
		{
			put("empty", new EmptyCommand());
			put("login", new LoginCommand(UserService.getInstance()));
			put("logout", new LogoutCommand());
			put("registration", new RegistrationCommand(UserService.getInstance()));
			put("userDetailUpdate", new UserDetailUpdateCommand(UserDetailsService.getInstance()));
			put("periodicalCreation", new PeriodicalCreationCommand(PeriodicalService.getInstance()));
			put("entryCreation", new EntryCreationCommand(EntryService.getInstance()));
			put("subscribe", new SubscribeCommand(SubscriptionService.getInstance()));
			put("paySubscriptionFee", new PaySubscriptionFeeCommand(SubscriptionService.getInstance()));
			

			put("getPeriodicalsSearchPage", new GetPeriodicalsSearchPageCommand(PeriodicalService.getInstance(),
																				CategoryService.getInstance()));
			put("getMainPage", new GetMainPageCommand());
			put("getPeriodicalCreationPage", new GetPeriodicalCreationPageCommand(CategoryService.getInstance()));
			put("getUserDetailsPage", new GetUserDetailsPageCommand(UserDetailsService.getInstance()));
			put("getUserDetailsUpdatePage", new GetUserDetailsUpdatePageCommand(UserDetailsService.getInstance()));
			put("getPeriodicalEntryCreationPage", new GetPeriodicalEntryCreationPageCommand(PeriodicalService.getInstance()));
			put("getEntryPage", new GetEntryPageCommand(EntryService.getInstance()));

		}
	};

	public Command defineCommand(HttpServletRequest req) {
		Command emptyComand = commands.get("empty");
		Command current;
		String action = req.getParameter("command");
		if (action == null || action.isEmpty()) {
			current = emptyComand;
		}
		if ((current = commands.get(action)) == null) {
			current = emptyComand;
		}
		System.out.println(current);
		return current;
	}

	public Command getCommand(String name) {
		return commands.get(name);
	}

}
