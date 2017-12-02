package persistent;

import com.mysql.jdbc.Connection;

public class AddressDA {

	public static int addAddress(int userId, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO address (street, city, state, zipcode, userID) VALUES ('"
				+ street + "', '" + city + "', '" + state + "', '" + zip + "', '" + userId + "')";
		Connection con = (Connection) DbAccessImpl.connect();
		
		return DbAccessImpl.create(con, query);
	}

	public static int editAddress(int id, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		String query = "UPDATE address SET street = '" + street + "', city = '" + city + "', state = '"
				+ state + "', zipcode = '" + zip + "' WHERE addressID = '" + id + "'";
		System.out.println(query);
		Connection con = (Connection) DbAccessImpl.connect();
		return DbAccessImpl.update(con, query);
	}

	public static int deleteAddress(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM address WHERE addressID = '" + id + "'";
		Connection con = (Connection) DbAccessImpl.connect();
		return DbAccessImpl.delete(con, query);
	}

}
