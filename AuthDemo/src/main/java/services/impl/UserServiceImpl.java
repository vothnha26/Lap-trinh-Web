package services.impl;

import services.UserService;
import models.User;
import services.UserDao;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();
	
	@Override
	public User Login(String username, String password) {
		User user = this.get(username);
		if(user != null && user.getPassWord().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}
	
}
