package service.impl;
import model.User;
import service.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		System.out.println(user);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}


	@Override
	public User get(String username) {
		return userDao.get(username);
	}
}