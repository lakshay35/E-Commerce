package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

public class OrdersDA {
	
	public static int addtoOrders(int orderNumber, int agencyID, String orderStatus, String orderDate, String shippingAddress, String billingAddress, String paymentMethod, String confirmationNumber, int userID, double orderTotal) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO orders(orderNumber,agencyID,orderStatus,orderDate,shippingAddress,billingAddress,paymentMethod,confirmationNumber,userID,orderTotal) VALUES (" + "'" + orderNumber + "', '" + agencyID + "', '" + orderStatus + "', '" + orderDate + "','" + shippingAddress + "', '" + billingAddress + "', '" + paymentMethod + "', '" + confirmationNumber + "', '" + userID + "', '" + orderTotal + "')";
		System.out.println(query);
		return DbAccessImpl.create(con, query);
	}
	
	public static int getMaxOrderNumber() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM onlinebookstoredb.orders where orderNumber = (SELECT MAX(orderNumber) from onlinebookstoredb.orders)";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		int value = 0;
		try {
			if (set.next()) {
				value = set.getInt("orderNumber");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value+1;
	}

}
