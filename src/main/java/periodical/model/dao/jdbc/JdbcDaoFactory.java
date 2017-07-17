package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import periodical.model.dao.CategoryDao;
import periodical.model.dao.DaoFactory;
import periodical.model.dao.PeriodicalDao;
import periodical.model.dao.PeriodicalEntryDao;
import periodical.model.dao.SubscriptionDao;
import periodical.model.dao.UserDao;
import periodical.model.dao.UserDetailsDao;

public class JdbcDaoFactory extends DaoFactory{
	
	private DataSource dataSource;
	public JdbcDaoFactory(){
		 try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/periodical");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Connection getConnection() {
		try {
			return  dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDao createUserDao(Connection connection) {
		
		return new JdbcUserDao(connection);
	}

	@Override
	public UserDetailsDao createUserDetailsDao(Connection connection) {
		
		return new JdbcUserDetailsDao(connection);
	}

	@Override
	public PeriodicalDao createPeriodicalDao(Connection connection) {
		// TODO Auto-generated method stub
		return new JdbcPeriodicalDao(connection);
	}

	@Override
	public PeriodicalEntryDao createPeriodicalEntryDao(Connection connection) {
		// TODO Auto-generated method stub
		return new JdbcPeriodicalEntryDao(connection);
	}

	@Override
	public SubscriptionDao createSubscriptionDao(Connection connection) {
		// TODO Auto-generated method stub
		 return new JdbcSubscriptionDao(connection);
	}

	@Override
	public CategoryDao createCategoryDao(Connection connection) {
		// TODO Auto-generated method stub
		return new JdbcCategoryDao(connection);
	}


	
}
