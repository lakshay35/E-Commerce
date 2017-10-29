package logic;

import object.User;

public class UserController {

	public int CreateNewUser(User newUser) {
		int value = newUser.createNewUser();
		return value;
	}

	public int checkLogin(String email, String pass) {
		// TODO Auto-generated method stub
		User user = new User();
		int value = user.checkLogin(email, pass);
		return value;
	}

	public User GetUserInfo(String email, String pass) {
		User user = new User();
		user = user.getUserInfo(email, pass);
		return user;
	}

}
