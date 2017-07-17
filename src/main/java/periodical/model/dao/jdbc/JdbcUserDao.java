package periodical.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import periodical.model.dao.UserDao;
import periodical.model.entity.Role;
import periodical.model.entity.User;

public class JdbcUserDao implements UserDao {
	
	public static final String FIND_ALL = "SELECT * FROM user";
	public static final String SELECT_FROM_USERS_BY_ID = "SELECT * FROM user WHERE id=?";
	public static final String SELECT_FROM_USERS_BY_EMAIL = "SELECT * FROM user WHERE email=?";
	public static final String INSERT_USER = "INSERT INTO user(email, password, role) VALUES(?,?,?)"; 
	public static final String UPDATE = "UPDATE user SET first_name=?, second_name=?,money=? where id=?";

	private static final String ID_COL = "id";
	private static final String EMAIL_COL = "email";
	private static final String PASSWORD_COL = "password";
	private static final String ROLE_COL = "role";

	
	Connection connection;
	
	public JdbcUserDao(Connection connection){
		this.connection=connection;
	}
	


	@Override
	public Optional<User> find(int id) {
		Optional<User> result = Optional.empty();
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_FROM_USERS_BY_ID)){
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if(resultSet.next()){
				result = Optional.of( extractUserFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}
	

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User insert(User user) {
		try(PreparedStatement query =
                connection.prepareStatement(INSERT_USER,
                		Statement.RETURN_GENERATED_KEYS)){
			query.setString(1,user.getEmail());
			query.setString(2, user.getPassword());
			query.setString(3, user.getRole().toString());
			query.executeUpdate();
			ResultSet keys =  query.getGeneratedKeys();
			if(keys.next()){
				user.setId(keys.getInt(1));
			}
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
		
	}
	@Override
	public User update(User e) {
		// TODO Auto-generated method stub
		return null;
		
	}
	@Override
	public User delete(int id) {
		// TODO Auto-generated method stub
		return null;
		
	}
	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> result = Optional.empty();
		try(PreparedStatement query =
                connection.prepareStatement(SELECT_FROM_USERS_BY_EMAIL)){
			query.setString(1, email);
			ResultSet resultSet = query.executeQuery();
			if(resultSet.next()){
				result = Optional.of( extractUserFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException(e);
		}
		return result;
	}

	private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
	
		return new User.Builder()
				.setId(resultSet.getInt(ID_COL))
				.setEmail(resultSet.getString(EMAIL_COL))
				.setPassword(resultSet.getString(PASSWORD_COL))
				.setRole(Role.valueOf(resultSet.getString(ROLE_COL)))
				.build();
	}


}
