package service;

import entity.Users;

public interface UserDao {
	//用户登录方法
	public boolean userLogin(Users user);
}
