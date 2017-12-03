package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import object.Address;

public class AddressDA {

	//adds an address for a user
	
	public static int addAddress(int userId, String street, String city, String state, String zip) {
		
		// query for SQL
		
		String query = "INSERT INTO address (street, city, state, zipcode, userID) VALUES ('"
				+ street + "', '" + city + "', '" + state + "', '" + zip + "', '" + userId + "')";
	
		//create connection to the database
		
		Connection con = (Connection) DbAccessImpl.connect();
		
		//run query 
		
		return DbAccessImpl.create(con, query);
	}

	//edits an address for a user
	
	public static int editAddress(int id, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		String query = "UPDATE address SET street = '" + street + "', city = '" + city + "', state = '"
				+ state + "', zipcode = '" + zip + "' WHERE addressID = '" + id + "'";
		System.out.println(query);
		Connection con = (Connection) DbAccessImpl.connect();
		return DbAccessImpl.update(con, query);
	}

	//deletes an address for a user
	
	public static int deleteAddress(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM address WHERE addressID = '" + id + "'";
		Connection con = (Connection) DbAccessImpl.connect();
		return DbAccessImpl.delete(con, query);
	}

	//fetches previous address so that new address is added above it
	
	public static int getMaxAddressID() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM address where addressID = (SELECT MAX(addressID) from address)";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		int value = 0;
		try {
			if (set.next()) {
				value = set.getInt("addressID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value+1;
	}
	
	//retrieves an address for a user
	
	public static ResultSet getAddress(int userID) {
		String query = "SELECT * FROM address WHERE userID = '" + userID + "'";
		ResultSet set = null;
		Connection con = (Connection) DbAccessImpl.connect();
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	//retrieves an address ID 
	
	public static Address getAddressById(int id) {
		String query = "SELECT * FROM address WHERE addressID = '" + id + "'";
		ResultSet set = null;
		Connection con = (Connection) DbAccessImpl.connect();
		set = DbAccessImpl.retrieve(con, query);
		Address address = new Address();
		try {
			while(set.next()) {
				address.setStreet(set.getString("street"));
				address.setCity(set.getString("city"));
				address.setState(set.getString("state"));
				address.setZip(set.getString("zipcode"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
}
