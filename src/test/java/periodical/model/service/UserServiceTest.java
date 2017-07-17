//package periodical.model.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//import java.sql.Connection;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.internal.stubbing.answers.DoesNothing;
//
//import periodical.model.dao.DaoFactory;
//import periodical.model.dao.UserDao;
//import periodical.model.entity.User;
//
//public class UserServiceTest {
//	
//	@Test
//	@Ignore
//	public void saveUserTest(){
//		UserDao userDao = mock(UserDao.class);
//		DaoFactory factory = mock(DaoFactory.class);
//		Connection connection = mock(Connection.class);
//		UserService userService = new UserService(factory);
//		String email = "qwe@qwe.com";	//anyString
//		String password ="123qwe";		//anyString
//		
//		doReturn(userDao).when(factory).createUserDao();
//		doReturn(connection).when(factory).getConnection();
//		
//		userService.saveUser(email,password);
//		
//		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
//
//		verify(factory).createUserDao();
//		verify(userDao).insert(argument.capture());
//		assertEquals(email, argument.getValue().getEmail());
//		assertEquals(password, argument.getValue().getPassword());
//
//	}
//	@Test
//	public void checkPasswordTest(){
//		UserDao userDao = mock(UserDao.class);
//		DaoFactory factory = mock(DaoFactory.class);
//		UserService userService = new UserService(factory);
//		
//		doReturn(userDao).when(factory).createUserDao();
//		
//		String email = "qwe@qwe.com";	
//		String rightPassword ="123qwe";
//		String wrongPassword ="123qwe";	
//		User testUser =new User.Builder().setEmail(email).setPassword(rightPassword).build();
//		
//		
//		
//	}
//
//}
