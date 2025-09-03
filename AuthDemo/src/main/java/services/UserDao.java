package services;

import models.*;

public interface UserDao {
	User get(String username);
}
