package logic;

import object.User;

public class UserController {

	public int CreateNewUser(User newUser) {
		int value = newUser.createNewUser();
		return value;
	}

}
