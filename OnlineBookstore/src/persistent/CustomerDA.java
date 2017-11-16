package persistent;


import com.mysql.jdbc.Connection;

public class CustomerDA {
	
	public int createNewCustomer(String fname, String lname, String email, String password, int code, Boolean subscribe)
	{
		int sub = 0;
		if (subscribe == true)
		{
			sub = 1;
		}
		else
		{
			sub = 0;
		}
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.user (fName, lName, email, userType, userPassword, userCode, subscribed) VALUES"
				+ " ('" + fname + "', '" + lname + "', '" + email + "', 'Customer', '" + password + "', '" + code + "', '" + sub + "')";
		System.out.println(query);
		int value = DbAccessImpl.create(con, query);
		DbAccessImpl.disconnect(con);;
		return value;
	}
}
