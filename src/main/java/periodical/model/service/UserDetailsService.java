package periodical.model.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import periodical.model.dao.DaoFactory;
import periodical.model.dao.PeriodicalDao;
import periodical.model.dao.SubscriptionDao;
import periodical.model.dao.UserDetailsDao;
import periodical.model.entity.Periodical;
import periodical.model.entity.Subscription;
import periodical.model.entity.User;
import periodical.model.entity.UserDetails;

public class UserDetailsService {
	private static final String UNDEFINE = "undefine";
	
	private DaoFactory factory;
	
	private static class Holder{
		private static final UserDetailsService INSTANCE = new UserDetailsService(DaoFactory.getInstance());
	}
	
	public static UserDetailsService getInstance(){
		return Holder.INSTANCE;
	}
	
	UserDetailsService(DaoFactory factory) {
		this.factory = factory;
	}
	
	public UserDetails updateUserDetails(User user, String firstName, String lastName){
		try(Connection connection = factory.getConnection()){
		UserDetailsDao userDetailsDao =factory.createUserDetailsDao(connection);
		UserDetails userDetails = new UserDetails.Builder()
				.setId(user.getId())
				.setFirstName(firstName)
				.setLastName(lastName)
				.build();
		return userDetailsDao.update(userDetails);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public Optional<UserDetails> getUserDetails(User user) {
		try(Connection connection = factory.getConnection()){
			Optional<UserDetails> userDetails = Optional.empty();
			UserDetailsDao userDetailsDao =factory.createUserDetailsDao(connection);
			
			userDetails = userDetailsDao.find(user.getId());
			return userDetails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public Optional<UserDetails> getUserDetailsWithPeriodicalsAndSubscriptions(User user) {
		Optional<UserDetails> result = Optional.empty();
		Optional<UserDetails> foundUserDetails = getUserDetails(user);
		if(foundUserDetails.isPresent()){
		try(Connection connection = factory.getConnection()){
			PeriodicalDao periodicalDao = factory.createPeriodicalDao(connection);
			List<Periodical> periodicals = periodicalDao.findPeriodicalsByPublisherId(user.getId());
			
			SubscriptionDao subscriptionDao = factory.createSubscriptionDao(connection);
			List<Subscription> subscriptions = subscriptionDao.findSubscriptionsBySubscriberId(user.getId());
			
			UserDetails userDetails = foundUserDetails.get();
			userDetails.setPeriodicals(periodicals);
			userDetails.setSubscriptions(subscriptions);
			
			result = Optional.of(userDetails);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		}
		return result;
		
			
	}
	
	
	void saveDefaultUserDetails(User user, Connection connection) {
		UserDetails defaultUserDetails = createDefaultUserDetails(user);
		UserDetailsDao userDetailsDao =factory.createUserDetailsDao(connection);
		userDetailsDao.insert(defaultUserDetails);	
	}
	
	
	
	private UserDetails createDefaultUserDetails(User user) {
		UserDetails defaultUserDetails = new UserDetails.Builder()
				.setFirstName(UNDEFINE)
				.setLastName(UNDEFINE)
				.setMoney(new BigDecimal(0))
				.build();
		defaultUserDetails.setId(user.getId());
		user.setUserDetails(defaultUserDetails);
		return defaultUserDetails;
	}
}
