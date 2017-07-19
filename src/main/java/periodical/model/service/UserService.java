package periodical.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import periodical.controller.dto.RegistrationInput;
import periodical.model.dao.DaoFactory;
import periodical.model.dao.UserDao;
import periodical.model.entity.Role;
import periodical.model.entity.User;

public class UserService {

	private DaoFactory factory;
	private UserDetailsService userDetailsService;
	
	private static class Holder{
		private static final UserService INSTANCE = new UserService(DaoFactory.getInstance(),UserDetailsService.getInstance());
	}
	
	public static UserService getInstance(){
		return Holder.INSTANCE;
		
	}
	UserService(DaoFactory factory,UserDetailsService userDetailsService) {
		this.factory = factory;
		this.userDetailsService = userDetailsService;
	}


	public User saveUser(RegistrationInput input) {
		User user = new User.Builder()
				.setEmail(input.getEmail())
				.setPassword(input.getFirstPassword())
				.setRole(Role.USER)
				.build();
		try(Connection connection = factory.getConnection()){
			connection.setAutoCommit(false);
			UserDao userDao = factory.createUserDao(connection);
			user = userDao.insert(user);
			userDetailsService.saveDefaultUserDetails(user, connection);
			connection.setAutoCommit(true);
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}	
	}

	public Optional<User> findUser(String email) {
		try(Connection connection = factory.getConnection()){
		UserDao userDao = factory.createUserDao(connection);
		Optional<User> user = userDao.findByEmail(email);
		return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}	
		
	}
	
	public boolean checkPassword(User user, String password){
		return password.equals(user.getPassword());
		
	}

	

}
