package persistent;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

import object.User;

public class UserDA {

	public User getUserInfo(String email2, String pass, Connection con) {
		String query = "SELECT * FROM user WHERE email = '" + email2 + "' AND userPassword = '" + pass + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		User user = new User();
		try
		{
			if (set.next())
			{
				user.setFname(set.getString("fName"));
				user.setLname(set.getString("lName"));
				user.setEmail(set.getString("email"));
				user.setUserId(set.getInt("userID"));
				user.setUserType(set.getString("userType"));
				user.setCode(set.getInt("userCode"));
				user.setStatus(set.getString("status"));
			}
			else
			{
				user = null;
			}
		}
		catch (Exception e)
		{
			user = null;
		}
		return user;
	}

	public int verifyAccount(String attribute) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE user SET status = 'verified' WHERE email = '" + attribute + "'";
		int value = DbAccessImpl.update(con, query);
		DbAccessImpl.disconnect(con);
		return value;
	}
	
	public ResultSet checkLogin(String email2, String pass, Connection con) {
		String query = "SELECT * FROM user WHERE email = '" + email2 + "' AND userPassword = '" + pass + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		return set;
	}

}
