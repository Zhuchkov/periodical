package periodical.model.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import periodical.model.dao.DaoFactory;
import periodical.model.dao.PeriodicalDao;
import periodical.model.dao.SubscriptionDao;
import periodical.model.dao.UserDetailsDao;
import periodical.model.entity.Periodical;
import periodical.model.entity.Subscription;
import periodical.model.entity.User;
import periodical.model.entity.UserDetails;

public class SubscriptionService {
	private DaoFactory factory;

	private static class Holder {
		private static final SubscriptionService INSTANCE = new SubscriptionService(DaoFactory.getInstance());
	}

	public static SubscriptionService getInstance() {
		return Holder.INSTANCE;
	}

	SubscriptionService(DaoFactory factory) {
		this.factory = factory;
	}


	public List<Subscription> getSubscriptionsBySubscriber(User user) {
		try (Connection connection = factory.getConnection()) {
			SubscriptionDao subscriptionDao = factory.createSubscriptionDao(connection);
			return subscriptionDao.findSubscriptionsBySubscriberId(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public Optional<Subscription> subscribeUser(User user, int periodicalId) {
		try (Connection connection = factory.getConnection()) {
			Optional<Subscription> subscription = Optional.empty();
			PeriodicalDao periodicalDao = factory.createPeriodicalDao(connection); 
			Optional<Periodical> periodical = periodicalDao.find(periodicalId);
			
			if(periodical.isPresent()){
				SubscriptionDao subscriptionDao = factory.createSubscriptionDao(connection);
				Optional<Subscription> existingSubscription = subscriptionDao.findBySubscriberAndPeriodical(user, periodical.get());
					if(!existingSubscription.isPresent()){
						Subscription newSubscription = createDefaultSubscription(user,periodical.get());
						subscription = Optional.of(subscriptionDao.insert(newSubscription));
					}
			}
			return subscription;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	public void paySubscriptionFee(int subscriptionId) {
	
		try (Connection connection = factory.getConnection()) {
			//connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			SubscriptionDao subscriptionDao = factory.createSubscriptionDao(connection);
			Optional<Subscription> optionalSubscription=subscriptionDao.find(subscriptionId);
			if(optionalSubscription.isPresent()&&optionalSubscription.get().isUpdated()){
				
				Subscription subscription = optionalSubscription.get();
				UserDetails subscriber = subscription.getSubscriber();
				Periodical periodical = subscription.getPeriodical();
				UserDetails publisher = periodical.getPublisher();
				BigDecimal cost = periodical.getCost();
				
				if(subscriber.getMoney().compareTo(cost) ==1 ){
				
					publisher.setMoney(publisher.getMoney().add(cost));
					subscriber.setMoney(subscriber.getMoney().subtract(cost));
					
					subscription.setUpdated(false);
					subscription.setLastAvailableEntryDate(LocalDateTime.now());					
					subscriptionDao.update(subscription);	
				}else{
					
				}
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	
	private Subscription createDefaultSubscription(User user, Periodical periodical) {
		UserDetails userDetailsStub = new UserDetails.Builder().setId(user.getId()).build();
		return new Subscription.Builder()
				.setSubscriber(userDetailsStub)
				.setPeriodical(periodical)
				.setUpdated(true)
				.setActive(true)
				.setLastAvailableEntryDate(LocalDateTime.MIN)
				.build();
	}
}
