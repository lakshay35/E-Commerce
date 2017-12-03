package persistent;

import com.mysql.jdbc.Connection;

public class AdminDA {

	//change user type for a particular user 
	
	public static int authorizeUser(int userID, int value) {
		// create connection to the database
		Connection con = (Connection) DbAccessImpl.connect();
		String type = "";
		int check = -1;
		//check what type user is
		if (value != 0)
		{
			if (value == 1)
			{
				type = "Customer";
			}
			else if (value == 2)
			{
				type = "Shipping";
			}
			else if (value == 3)
			{
				type = "SystemAdmin";
			}
			else if (value == 4)
			{
				type = "Manager";
			}
			//update the user type to something else
			String query = "UPDATE user SET userType = '" + type + "' WHERE userID = '" + userID + "'";
			//run query
			check = DbAccessImpl.update(con, query);
		}
		return check;
	}

	//suspend a user
	public static int suspendUser(int userID) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE user SET status = 'suspended' WHERE userID = '" + userID + "'";
		int check = DbAccessImpl.update(con, query);
		return check;
	}

	//revoke suspension of a user
	public static int unsuspendUser(int userID) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE user SET status = 'verified' WHERE userID = '" + userID + "'";
		int check = DbAccessImpl.update(con, query);
		return check;
	}

}
