package junit.test;

import java.util.Date;

import org.junit.Test;

import com.jdbc.dao.DaoFactory;
import com.jdbc.dao.UserDao;
//import com.jdbc.dao.impl.UserDaoJdbcImpl;
import com.jdbc.domain.User;

public class UserDaoTest {
	
	@Test
	public void addUserTest() {
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		//UserDao userDao = new UserDaoJdbcImpl();//we no longer need com.jdbc.dao.impl.UserDaoJdbcImpl
		User user = new User();		
		user.setBirthday(new Date());
		user.setUsername("testname1");
		user.setMoney(400.0f);	
		
		userDao.addUser(user);
	}
	
	/*@Test
	public void findUserTest(){
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		//UserDao userDao = new UserDaoJdbcImpl();
		String loginName = "testname1";
		String password = "123";
		User user = userDao.findUser(loginName, password);
		System.out.println(user.getUsername());
	}*/
	
	/*@Test
	public void updateTest(){
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		User user = userDao.getUser(4);
		user.setMoney(1000.0f);
		userDao.update(user);
		System.out.println(user.getMoney());
	}*/
	
	/*@Test
	public void deleteTest(){
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		//UserDao userDao = new UserDaoJdbcImpl();
		User user = userDao.getUser(5);
		userDao.delete(user);
	}*/
}
