package periodical.model.dao;

import java.sql.Connection;

import periodical.model.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {

	private static class Holder {

		private static final DaoFactory JDBC_DAO_FACTORY = new JdbcDaoFactory();;
	}

	public static DaoFactory getInstance() {
			return Holder.JDBC_DAO_FACTORY;
		

	}

	public abstract Connection getConnection();
	
	public abstract UserDao createUserDao(Connection connection);

	public abstract UserDetailsDao createUserDetailsDao(Connection connection);

	public abstract PeriodicalDao createPeriodicalDao(Connection connection);

	public abstract PeriodicalEntryDao createPeriodicalEntryDao(Connection connection);

	public abstract SubscriptionDao createSubscriptionDao(Connection connection);
	
	public abstract CategoryDao createCategoryDao(Connection connection);


}
