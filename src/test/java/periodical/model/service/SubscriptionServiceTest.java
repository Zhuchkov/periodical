package periodical.model.service;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Optional;

import org.junit.Test;

import periodical.model.dao.DaoFactory;
import periodical.model.dao.SubscriptionDao;
import periodical.model.entity.Periodical;
import periodical.model.entity.Subscription;
import periodical.model.entity.UserDetails;
public class SubscriptionServiceTest {

	private static final BigDecimal PUBLISHER_MONEY = new BigDecimal(1000);
	private static final BigDecimal SUBSCRIBER_MONEY = new BigDecimal(1000);
	private static final BigDecimal PERIODICAL_COST = new BigDecimal(100);
	
	@Test
	public void paySubscriptionFeeEnoughMoneyForPayTest(){
		DaoFactory factory = mock(DaoFactory.class);
		Connection connection = mock(Connection.class);
		SubscriptionDao subscriptionDao = mock(SubscriptionDao.class);
		UserDetails subscriber = new UserDetails.Builder().setMoney(SUBSCRIBER_MONEY).build();
		UserDetails publisher = new UserDetails.Builder().setMoney(PUBLISHER_MONEY).build();
		Periodical periodical = new Periodical.Builder()
				.setCost(PERIODICAL_COST)
				.setPublisher(publisher)
				.build();
		Subscription subscription = new Subscription.Builder()
				.setUpdated(true)
				.setSubscriber(subscriber)
				.setPeriodical(periodical)
				.build();
		
		doReturn(connection).when(factory).getConnection();
		doReturn(subscriptionDao).when(factory).createSubscriptionDao(connection);
		doReturn(Optional.of(subscription)).when(subscriptionDao).find(anyInt());
		SubscriptionService service = new SubscriptionService(factory);
		service.paySubscriptionFee(1);
		BigDecimal expectedSubscriberMoney = SUBSCRIBER_MONEY.subtract(PERIODICAL_COST);
		BigDecimal expectedPublisherMoney = PUBLISHER_MONEY.add(PERIODICAL_COST);
		BigDecimal realSubscriberMoney = subscription.getSubscriber().getMoney();
		BigDecimal realPublisherMoney = subscription.getPeriodical().getPublisher().getMoney();
		assertEquals(expectedSubscriberMoney,realSubscriberMoney);
		assertEquals(expectedPublisherMoney,realPublisherMoney);
	}
	@Test
	public void paySubscriptionFeeNotEnoughMoneyForPayTest(){
		DaoFactory factory = mock(DaoFactory.class);
		Connection connection = mock(Connection.class);
		SubscriptionDao subscriptionDao = mock(SubscriptionDao.class);
		UserDetails subscriber = new UserDetails.Builder().setMoney(BigDecimal.ZERO).build();
		UserDetails publisher = new UserDetails.Builder().setMoney(PUBLISHER_MONEY).build();
		Periodical periodical = new Periodical.Builder()
				.setCost(PERIODICAL_COST)
				.setPublisher(publisher)
				.build();
		Subscription subscription = new Subscription.Builder()
				.setUpdated(true)
				.setSubscriber(subscriber)
				.setPeriodical(periodical)
				.build();
		
		doReturn(connection).when(factory).getConnection();
		doReturn(subscriptionDao).when(factory).createSubscriptionDao(connection);
		doReturn(Optional.of(subscription)).when(subscriptionDao).find(anyInt());
		SubscriptionService service = new SubscriptionService(factory);
		service.paySubscriptionFee(1);
		BigDecimal expectedSubscriberMoney = BigDecimal.ZERO;
		BigDecimal expectedPublisherMoney = PUBLISHER_MONEY;
		BigDecimal realSubscriberMoney = subscription.getSubscriber().getMoney();
		BigDecimal realPublisherMoney = subscription.getPeriodical().getPublisher().getMoney();
		assertEquals(expectedSubscriberMoney,realSubscriberMoney);
		assertEquals(expectedPublisherMoney,realPublisherMoney);
	}
}
