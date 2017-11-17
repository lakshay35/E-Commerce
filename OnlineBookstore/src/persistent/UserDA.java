package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	public int changePassword(String email2, String oldPassword, String newPassword) {


        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT * FROM user WHERE email = '" + email2 + "'";
        ResultSet set = DbAccessImpl.retrieve(con, query);
        int id = 0;
        int value;
        try {
        	if (set.next())
        	{
        		System.out.println(oldPassword);
        		System.out.println(newPassword);
        		if (oldPassword.equals(set.getString("userPassword")))
        		{
	        		id = set.getInt("userID");
	        		query = "UPDATE user SET userPassword = '" + newPassword + "' WHERE userID = " + id;
	                value = DbAccessImpl.update(con, query);
        		}
        		else
        		{
        			value = 0;
        		}
        	}
        	else
        	{
        		value = 0;
        	}
        } catch (Exception e) {
            value = 0;
            e.printStackTrace();
        }
        DbAccessImpl.disconnect(con);
        return value;
	}

	public int recoverPassword(String email2, String newPass) {
		// TODO Auto-generated method stub

        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT * FROM user WHERE email = '" + email2 + "'";
        ResultSet set = DbAccessImpl.retrieve(con, query);
        int id;
        int value = 0;
        try {
			if (set.next())
			{
				id = set.getInt("userID");
	        	query = "UPDATE user SET userPassword = '" + newPass + "' WHERE userID = '" + id + "'";
	        	value = DbAccessImpl.update(con, query);
			}
		} catch (SQLException e) {
			
		}
        DbAccessImpl.disconnect(con);
		return value;
	}

	public static ResultSet getUsers(Connection con) {
		// TODO Auto-generated method stub
		String query = "SELECT userID, userType, fName, lName, email, status FROM user";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		return set;
	}

}
