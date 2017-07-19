package periodical.controller.command;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import periodical.controller.validation.ValidatorFactory;
import periodical.model.service.CategoryService;
import periodical.model.service.EntryService;
import periodical.model.service.PeriodicalService;
import periodical.model.service.SubscriptionService;
import periodical.model.service.UserDetailsService;
import periodical.model.service.UserService;

public class CommandFactory {
	
	private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
	
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
			put("login", new LoginCommand(UserService.getInstance(),ValidatorFactory.getInstance()));
			put("getLoginPage", new GetLoginPageCommand());
			put("getRegisterPage", new GetRegisterPageCommand());
			put("logout", new LogoutCommand());
			put("setLocale", new SetLocaleCommand());
			put("registration", new RegistrationCommand(UserService.getInstance(), ValidatorFactory.getInstance()));
			put("userDetailUpdate", new UserDetailUpdateCommand(UserDetailsService.getInstance(),ValidatorFactory.getInstance()));
			put("periodicalCreation", new PeriodicalCreationCommand(PeriodicalService.getInstance(),ValidatorFactory.getInstance()));
			put("entryCreation", new EntryCreationCommand(EntryService.getInstance(),ValidatorFactory.getInstance()));
			put("subscribe", new SubscribeCommand(SubscriptionService.getInstance()));
			put("paySubscriptionFee", new PaySubscriptionFeeCommand(SubscriptionService.getInstance()));
			

			put("getPeriodicalsSearchPage", new GetPeriodicalsSearchPageCommand(PeriodicalService.getInstance(),
																				CategoryService.getInstance(),
																				ValidatorFactory.getInstance()));
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
			LOGGER.info("No command param found");
		}
		if ((current = commands.get(action)) == null) {
			current = emptyComand;
			LOGGER.info("Comand "+action+" not found");
		}
		LOGGER.info("Comand "+current+" returned");
		return current;
	}

	public Command getCommand(String name) {
		return commands.get(name);
	}

}
