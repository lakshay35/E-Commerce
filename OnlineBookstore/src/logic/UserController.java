package logic;

import object.User;
import object.UserProfile;
import persistent.UserDA;

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


	public int verifyAccount(String attribute) {
		// TODO Auto-generated method stub
		User user = new User();
		int value = user.verify(attribute);
		return value;
	}

	public int changePassword(String email, String oldPassword, String newPassword) {
		User user = new User();
		int value = user.changePassword(email, oldPassword, newPassword);
		return value;
	}

	public int recoverPassword(String email, String host, String user2, String port, String pass) {
		// TODO Auto-generated method stub
		User user = new User();
		int value = user.recoverPassword(email, host, user2, port, pass);
		return value;
	}

	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		User user = new User();
		return user.checkEmail(email);
	}

	public UserProfile viewProfile(String email) {
		UserDA da = new UserDA();
        return da.getDetails(email);
    }

}
