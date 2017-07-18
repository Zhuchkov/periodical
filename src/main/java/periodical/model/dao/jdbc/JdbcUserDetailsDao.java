package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import periodical.model.dao.UserDetailsDao;
import periodical.model.entity.UserDetails;

public class JdbcUserDetailsDao implements UserDetailsDao{

	private static final String INSERT_USER_DETAILS_BY_ID = "INSERT INTO user_detail(id ,first_name, last_name, money)VALUES(?,?,?,?) ";
	private static final String UPDATE_USER_DETAILS_BY_ID = "UPDATE  user_detail SET first_name = ?, last_name = ? WHERE id = ?";
	private static final String SELECT_USER_DETAILS_BY_ID ="SELECT * FROM user_detail WHERE id=?";
	Connection connection;
	public JdbcUserDetailsDao(Connection connection){
		this.connection=connection;
	}
	
	@Override
	public Optional<UserDetails> find(int id) {
		Optional<UserDetails> result = Optional.empty();
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_USER_DETAILS_BY_ID)){
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if(resultSet.next()){
				result = Optional.of( extractUserDetailsFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}


	@Override
	public List<UserDetails> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails insert(UserDetails userDetails) {
		try(PreparedStatement query =
                connection.prepareStatement(INSERT_USER_DETAILS_BY_ID)){
			query.setInt(1, userDetails.getId());
			query.setString(2, userDetails.getFirstName());
			query.setString(3, userDetails.getLastName());
			query.setBigDecimal(4, userDetails.getMoney());
			query.executeUpdate();
			return userDetails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDetails update(UserDetails userDetails) {
		try(PreparedStatement query =
                connection.prepareStatement(UPDATE_USER_DETAILS_BY_ID)){
			query.setString(1, userDetails.getFirstName());
			System.out.println(userDetails.getFirstName());
			query.setString(2, userDetails.getLastName());
			query.setInt(3, userDetails.getId());
			query.executeUpdate();
			return userDetails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDetails delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDetails extractUserDetailsFromResultSet(ResultSet resultSet) throws SQLException {
		return new UserDetails.Builder()
				.setId(resultSet.getInt("id"))
				.setFirstName(resultSet.getString("first_name"))
				.setLastName(resultSet.getString("last_name"))
				.setMoney(resultSet.getBigDecimal("money"))
				.build();
	}

}
