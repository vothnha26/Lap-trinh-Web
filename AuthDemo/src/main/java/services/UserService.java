package services;

import models.*;

public interface UserService {
	User Login(String username, String password);
	User get(String username);
}
