package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import periodical.model.dao.DaoConnection;

public class JdbcDaoConnection implements DaoConnection {

	private Connection connection;

	JdbcDaoConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void begin() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void commit() {
		try {
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void rollback() {
		try {
			connection.rollback();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		try {
			if (!connection.getAutoCommit()) {
				rollback();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
