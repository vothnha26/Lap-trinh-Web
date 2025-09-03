package service;
import model.*;
import model.User.ResetPasswordStatus;

public interface UserDao {
	User get(String username);
	ResetPasswordStatus resetPassword(String name, String email, String newPassword, String confirmPassword);
	void insert(User user); 
	boolean checkExistEmail(String email); 
	boolean checkExistUsername(String username); 
	boolean checkExistPhone(String phone);
}
