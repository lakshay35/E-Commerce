package object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import persistent.AdminDA;
import persistent.DbAccessImpl;
import persistent.UserDA;

public class SystemAdmin extends User {
	
	public SystemAdmin () {
		
	}

	public List<User> viewUsers() {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = UserDA.getUsers(con);
		List<User> userList = new ArrayList<User>();
		
		try {
			while (set.next())
			{
				User user = new User();
				user.setUserId(set.getInt("userID"));
				user.setUserType(set.getString("userType"));
				user.setFname(set.getString("fName"));
				user.setLname(set.getString("lName"));
				user.setEmail(set.getString("email"));
				user.setStatus(set.getString("status"));
				userList.add(user);
			}
		} catch (SQLException e) {
			userList.clear();
		}
		return userList;
	}

	public int authorizeUser(int userID, int value) {
		// TODO Auto-generated method stub
		return AdminDA.authorizeUser(userID, value);
	}

	public int suspendUser(int userID) {
		return AdminDA.suspendUser(userID);
	}

	public int unsuspendUser(int userID) {
		// TODO Auto-generated method stub
		return AdminDA.unsuspendUser(userID);
	}

}
