package persistent;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

import object.User;

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

	public ResultSet checkLogin(String email2, String pass) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM user WHERE email = '" + email2 + "' AND userPassword = '" + pass + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		return set;
	}
}
