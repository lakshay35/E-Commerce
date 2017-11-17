package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class CartDA {
	public static int addtocartDA(int cartID, int userID, int promoID, int isbn, int qty, double total) {
		int value = 0;
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM onlinebookstoredb.cart WHERE isbn = '" + isbn + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		
		try {
			if(set.next()) {
				qty = set.getInt("qty") + 1;
				total = qty*total;
				query = "UPDATE onlinebookstoredb.cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "'";
				System.out.println(query);
				value = DbAccessImpl.update(con, query);
			}else{
				query= "INSERT INTO onlinebookstoredb.cart (cartID, userID, promoID, isbn, qty, total) VALUES"
						+ " ('" + cartID + "', '" + userID + "', '" + promoID + "','" + isbn + "', '" + qty + "', '" + total + "')";
				System.out.println(query);
				value = DbAccessImpl.update(con, query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public static int getCartID() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM onlinebookstoredb.cart where cartID = (SELECT MAX(cartID) from onlinebookstoredb.cart)";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		int value = 0;
		try {
			if (set.next()) {
				value = set.getInt("cartID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value+1;
	}
	
	public static int deleteFromCartDA(int userID, int isbn) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM onlinebookstoredb.cart where userID = '" + userID + "'AND isbn = '" + isbn +"'";
		System.out.println(query);
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	public static ResultSet getCart(int userID, Connection con) {
		String query = "SELECT * FROM onlinebookstoredb.cart WHERE userID = '" + userID + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
}
