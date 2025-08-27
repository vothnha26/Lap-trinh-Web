package service.impl;
import model.User;
import service.UserDao;
import service.UserService;

import model.*;

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
	    // 1. Kiểm tra xem username đã tồn tại chưa
	    if (userDao.checkExistUsername(username)) {
	        return false; // Nếu đã tồn tại, trả về false và kết thúc
	    }
	    
	    // 2. Lấy ngày giờ hiện tại
	    long millis = System.currentTimeMillis();
	    java.sql.Date registrationDate = new java.sql.Date(millis);
	    
	    // 3. Tạo một đối tượng User mới
	    // Giả sử: roleId = 5 (ví dụ: role người dùng thường), avatar = null
	    User newUser = new User(email, username, fullname, password, null, 5, phone, registrationDate);
	    
	    // 4. Thêm người dùng mới vào cơ sở dữ liệu
	    userDao.insert(newUser);
	    
	    return true; // Trả về true để xác nhận đăng ký thành công
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
}