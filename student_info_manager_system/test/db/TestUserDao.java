package db;

import org.junit.jupiter.api.Test;

import entity.Users;
import service.UserDao;
import service.impl.UserDaoImpl;

public class TestUserDao {

	@Test
	public void TestUserLogin() {
		Users user = new Users(1,"x","x");
		UserDao userDao = new UserDaoImpl();
		if(userDao.userLogin(user)) {
			System.out.println("成功登录");
		}else {
			System.out.println("登录失败");
		}
	}
	

}
