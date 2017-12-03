package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class CartDA {
	public static int addtocartDA(int cartID, int userID, int promoID, int isbn, int qty, double total) {
		int value = 0;
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM finalonlinebookstore.cart WHERE isbn = '" + isbn + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		
		try {
			if(set.next()) {
				qty = set.getInt("qty") + 1;
				total = qty*total;
				query = "UPDATE finalonlinebookstore.cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "'";
				System.out.println(query);
				value = DbAccessImpl.update(con, query);
			}else{
				query= "INSERT INTO finalonlinebookstore.cart (cartID, userID, promoID, isbn, qty, total) VALUES"
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
		String query = "SELECT * FROM finalonlinebookstore.cart where cartID = (SELECT MAX(cartID) from finalonlinebookstore.cart)";
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
	
	public static int updateCart(int userID, int isbn, int qty, double total) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "UPDATE finalonlinebookstore.cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "' AND userID='" + userID + "'";
		return DbAccessImpl.update(con, query);
	}
	
	public static int deleteFromCartDA(int userID, int isbn) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM finalonlinebookstore.cart where userID = '" + userID + "'AND isbn = '" + isbn +"'";
		System.out.println(query);
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	public static int deleteCartItems(int userID) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM finalonlinebookstore.cart where userID = '" + userID + "'";
		System.out.println(query);
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	public static ResultSet getCart(int userID, Connection con) {
		String query = "SELECT * FROM finalonlinebookstore.cart WHERE userID = '" + userID + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	public static ResultSet getCartByID(int id) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM finalonlinebookstore.cart WHERE cartID = '" + id + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
}
