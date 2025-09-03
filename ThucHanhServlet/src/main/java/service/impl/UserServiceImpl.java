package service.impl;
import model.User;
import model.User.ResetPasswordStatus;
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

	@Override
	public void insert(User user) {
	    userDao.insert(user);
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
	    if (userDao.checkExistUsername(username)) {
	        return false;
	    }
	    
	    long millis = System.currentTimeMillis();
	    java.sql.Date registrationDate = new java.sql.Date(millis);
	    
	    User newUser = new User(email, username, fullname, password, null, 5, phone, registrationDate);
	    
	    userDao.insert(newUser);
	    
	    return true;
	}

	
	@Override
	public boolean checkExistUsername(String username) {
	    return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
	    return userDao.checkExistPhone(phone);
	}
	
	@Override
	public boolean checkExistEmail(String email) {
	    return userDao.checkExistEmail(email);
	}


	@Override
	public ResetPasswordStatus resetPassword(String name, String email, String newPassword, String confirmPassword) {
		return userDao.resetPassword(name, email, newPassword, confirmPassword);
	}
}