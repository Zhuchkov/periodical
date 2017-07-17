package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
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

public class JdbcSubscriptionDao implements SubscriptionDao {

	private static final String SELECT_SUBSCRIPTION_BY_SUBSCRIBER = "SELECT subscription.id,subscription.last_available_entry_date, subscription.updated,subscription.active,subscriber.id, subscriber.first_name, subscriber.last_name, subscriber.money,  periodical.id, periodical.cost, periodical.name FROM subscription JOIN user_detail subscriber ON subscriber_id = subscriber.id JOIN periodical ON periodical.id = periodical_id  where subscriber_id = ?";
	private static final String SELECT_SUBSCRIPTION_BY_PERIODICAL = "SELECT subscription.id,subscription.last_available_entry_date, subscription.updated,subscription.active,subscriber.id, subscriber.first_name, subscriber.last_name, subscriber.money,  periodical.id, periodical.cost, periodical.name FROM subscription JOIN user_detail subscriber ON subscriber_id = subscriber.id JOIN periodical ON periodical.id = periodical_id  where periodical_id = ?";
	private static final String SELECT_SUBSCRIPTION_BY_PERIODICAL_AND_SUBSCRIBER = "SELECT subscription.id,subscription.last_available_entry_date, subscription.updated,subscription.active,subscriber.id, subscriber.first_name, subscriber.last_name, subscriber.money,  periodical.id, periodical.cost, periodical.name FROM subscription JOIN user_detail subscriber ON subscriber_id = subscriber.id JOIN periodical ON periodical.id = periodical_id  where periodical_id = ? AND subscriber_id = ?";
	
	private static final String SELECT_SUBSCRIPTION_WITH_PUBLISHER_BY_ID = "SELECT subscription.id,subscription.last_available_entry_date, subscription.updated,subscription.active,subscriber.id, subscriber.first_name, subscriber.last_name, subscriber.money,  periodical.id, periodical.cost, periodical.name,  publisher.id, publisher.first_name, publisher.last_name, publisher.money FROM subscription JOIN user_detail subscriber ON subscriber_id = subscriber.id JOIN periodical ON periodical.id = periodical_id JOIN user_detail publisher ON publisher_id = publisher.id WHERE subscription.id = ? FOR UPDATE";
	
	private static final String UPDATE_SUBSCRIPTION = "UPDATE subscription JOIN user_detail subscriber ON subscriber_id = subscriber.id JOIN periodical ON periodical.id = periodical_id JOIN user_detail publisher ON publisher_id = publisher.id  SET last_available_entry_date = ?, updated = ?, active =?, publisher.money=?,subscriber.money=? WHERE subscription.id = ? ";
	public static final String INSERT_SUBSCRIPTION = "INSERT INTO subscription(subscriber_id, periodical_id,last_available_entry_date,updated,active) VALUES(?,?,?,?,?)";
	private static final String NOTIFY_SUBSCRIBERS = "UPDATE subscription SET updated=true WHERE periodical_id = ?";
	
	private static final String ID_COL = "id";
	private static final String SUBSCRIBER_ID_COL = "subscriber_id";
	private static final String PERIODICAL_ID_COL = "periodical_id";
	private static final String UPDATED_COL = "updated";
	private static final String ACTIVE_COL = "active";
	
	private static final String SUBSCRIBER_ID = "subscriber.id";
	private static final String SUBSCRIBER_FIRST_NAME = "subscriber.first_name";
	private static final String SUBSCRIBER_LAST_NAME = "subscriber.last_name";
	private static final String SUBSCRIBER_MONEY = "subscriber.money";
	
	private static final String PUBLISHER_ID = "publisher.id";
	private static final String PUBLISHER_FIRST_NAME = "publisher.first_name";
	private static final String PUBLISHER_LAST_NAME = "publisher.last_name";
	private static final String PUBLISHER_MONEY = "publisher.money";
	
	private static final String PERIODICAL_ID = "periodical.id";
	private static final String PERIODICAL_NAME = "periodical.name";
	private static final String PERIODICAL_COST = "periodical.cost";
	
	private static final String SUBSCRIPTION_ID = "subscription.id";
	private static final String SUBSCRIPTION_ACTIVE = "subscription.active";
	private static final String SUBSCRIPTION_UPDATED = "subscription.updated";
	private static final String LAST_AVAILABLE_DATE = "subscription.last_available_entry_date";

	Connection connection;

	UserDetailsDao userDetailsDao;
	PeriodicalDao periodicalDao;

	public JdbcSubscriptionDao(Connection connection) {
		this.connection = connection;
		this.userDetailsDao = DaoFactory.getInstance().createUserDetailsDao(connection);
		this.periodicalDao = DaoFactory.getInstance().createPeriodicalDao(connection);
	}

	@Override
	public Subscription insert(Subscription subscription) {
		try (PreparedStatement query = connection.prepareStatement(INSERT_SUBSCRIPTION,
				Statement.RETURN_GENERATED_KEYS)) {
			query.setInt(1, subscription.getSubscriber().getId());
			query.setInt(2, subscription.getPeriodical().getId());
			query.setLong(3, subscription.getLastAvailableEntryDate().toEpochSecond(ZoneOffset.UTC));
			query.setBoolean(4, subscription.isUpdated());
			query.setBoolean(5, subscription.isActive());
			query.executeUpdate();
			ResultSet keys = query.getGeneratedKeys();
			if (keys.next()) {
				subscription.setId(keys.getInt(1));
			}
			return subscription;
		} catch (SQLException e) {
			// TODO Auto-generated method stub
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Optional<Subscription> find(int id) {
		try (PreparedStatement query = connection.prepareStatement(SELECT_SUBSCRIPTION_WITH_PUBLISHER_BY_ID)) {
			Optional<Subscription> subscription = Optional.empty();
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if (resultSet.next()) {
				subscription  = Optional.of((extractSubscriptionWithSubscriberAndPublisher(resultSet)));
			}
			return subscription;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	

	@Override
	public List<Subscription> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription update(Subscription subscription) {
		try(PreparedStatement query =
                connection.prepareStatement(UPDATE_SUBSCRIPTION)){
			query.setLong(1, subscription.getLastAvailableEntryDate().toEpochSecond(ZoneOffset.UTC));
			query.setBoolean(2, subscription.isUpdated());
			query.setBoolean(3, subscription.isActive());
			query.setBigDecimal(4, subscription.getPeriodical().getPublisher().getMoney());
			query.setBigDecimal(5, subscription.getSubscriber().getMoney());
			query.setInt(6, subscription.getId());
			query.executeUpdate();
			return subscription;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Subscription delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subscription> findSubscriptionsBySubscriberId(int subscriberId) {
		try (PreparedStatement query = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_SUBSCRIBER)) {
			List<Subscription> subscriptions = new LinkedList<>();
			query.setInt(1, subscriberId);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				subscriptions.add(extractSubscriptionWithSubscriberAndPeriodical(resultSet));
			}
			return subscriptions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Subscription> findSubscriptionsByPeriodicalId(int periodicalId) {
		try (PreparedStatement query = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_PERIODICAL)) {
			List<Subscription> subscriptions = new LinkedList<>();
			query.setInt(1, periodicalId);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				subscriptions.add(extractSubscriptionWithSubscriberAndPeriodical(resultSet));
			}
			return subscriptions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public void notifySubscribers(Periodical periodical) {
		try (PreparedStatement query = connection.prepareStatement(NOTIFY_SUBSCRIBERS)) {
			query.setInt(1, periodical.getId());
			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	@Override
	public Optional<Subscription> findBySubscriberAndPeriodical(User subscriber, Periodical periodical) {
		try (PreparedStatement query = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_PERIODICAL_AND_SUBSCRIBER)) {
			Optional<Subscription> subscription = Optional.empty();
			query.setInt(1, periodical.getId());
			query.setInt(2, subscriber.getId());
			ResultSet resultSet = query.executeQuery();
			if (resultSet.next()) {
				subscription = Optional.of(extractSubscriptionWithSubscriberAndPeriodical(resultSet));
			}
			return subscription;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	private Subscription extractSubscriptionWithSubscriberAndPublisher(ResultSet resultSet) throws SQLException {
		Subscription subscription = extractSubscriptionWithSubscriberAndPeriodical(resultSet);

		UserDetails publisher = extractPublisher(resultSet);
		Periodical periodical = subscription.getPeriodical();
		periodical.setPublisher(publisher);
		subscription.setPeriodical(periodical);
		
		return subscription;
	}

	private Subscription extractSubscriptionWithSubscriberAndPeriodical(ResultSet resultSet) throws SQLException {
		Subscription subscription = extractSubscription(resultSet);
		UserDetails subscriber = extractSubscriber(resultSet);
		Periodical periodical = extractPeriodical(resultSet);
		subscription.setSubscriber(subscriber);
		subscription.setPeriodical(periodical);
		return subscription;

	}

	
	private Subscription extractSubscription(ResultSet resultSet) throws SQLException {
		return new Subscription.Builder()
				.setId(resultSet.getInt(SUBSCRIPTION_ID))
				.setActive(resultSet.getBoolean(SUBSCRIPTION_ACTIVE))
				.setUpdated(resultSet.getBoolean(SUBSCRIPTION_UPDATED))
				.setLastAvailableEntryDate(LocalDateTime.ofEpochSecond(resultSet.getLong(LAST_AVAILABLE_DATE), 0, ZoneOffset.UTC))
				.build();
	}
	private Periodical extractPeriodical(ResultSet resultSet) throws SQLException {
		return new Periodical.Builder()
				.setId(resultSet.getInt(PERIODICAL_ID))
				.setName(resultSet.getString(PERIODICAL_NAME))
				.setCost(resultSet.getBigDecimal(PERIODICAL_COST))
				.build();
	}
	private UserDetails extractPublisher(ResultSet resultSet) throws SQLException {
		return new UserDetails.Builder()
				.setId(resultSet.getInt(PUBLISHER_ID))
				.setFirstName(resultSet.getString(PUBLISHER_FIRST_NAME))
				.setLastName(resultSet.getString(PUBLISHER_LAST_NAME))
				.setMoney(resultSet.getBigDecimal(PUBLISHER_MONEY))
				.build();
	}
	private UserDetails extractSubscriber(ResultSet resultSet) throws SQLException {
		return new UserDetails.Builder()
				.setId(resultSet.getInt(SUBSCRIBER_ID))
				.setFirstName(resultSet.getString(SUBSCRIBER_FIRST_NAME))
				.setLastName(resultSet.getString(SUBSCRIBER_LAST_NAME))
				.setMoney(resultSet.getBigDecimal(SUBSCRIBER_MONEY))
				.build();
	}
}
