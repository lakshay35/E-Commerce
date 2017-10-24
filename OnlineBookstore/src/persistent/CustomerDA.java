package persistent;

import com.mysql.jdbc.Connection;

public class CustomerDA {
	
	public int createNewCustomer(String fname, String lname, String email, String password)
	{
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.user (fName, lName, email, userType, userPassword) VALUES"
				+ " ('" + fname + "', '" + lname + "', '" + email + "', 'Customer', '" + password + "')";
		System.out.println(query);
		int value = DbAccessImpl.create(con, query);
		return value;
	}
}
