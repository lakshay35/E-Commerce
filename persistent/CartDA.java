package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class CartDA {
	//add a book to the cart
	public static int addtocartDA(int cartID, int userID, int promoID, int isbn, int qty, double total) {
		int value = 0;
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM cart WHERE isbn = '" + isbn + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		
		try {
			if(set.next()) {
				qty = set.getInt("qty") + 1;
				total = qty*total;
				query = "UPDATE cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "'";
				System.out.println(query);
				value = DbAccessImpl.update(con, query);
			}else{
				query= "INSERT INTO cart (cartID, userID, promoID, isbn, qty, total) VALUES"
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
	
	//returns the ID of a particular customers cart
	public static int getCartID() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM cart where cartID = (SELECT MAX(cartID) from cart)";
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
	
	//update a quantity in an existing cart
	public static int updateCart(int userID, int isbn, int qty, double total) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "UPDATE cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "' AND userID='" + userID + "'";
		return DbAccessImpl.update(con, query);
	}
	
	//delete something from the cart
	public static int deleteFromCartDA(int userID, int isbn) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM cart where userID = '" + userID + "'AND isbn = '" + isbn +"'";
		System.out.println(query);
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	//delete the entire cart
	public static int deleteCartItems(int userID) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM cart where userID = '" + userID + "'";
		System.out.println(query);
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	//returns an SQL set containing the contents within a cart
	public static ResultSet getCart(int userID, Connection con) {
		String query = "SELECT * FROM cart WHERE userID = '" + userID + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	//returns an SQL set with cart contents for a particular cart
	public static ResultSet getCartByID(int id) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM cart WHERE cartID = '" + id + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
}
