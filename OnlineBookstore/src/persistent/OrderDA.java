package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

import object.Address;
import object.Order;

public class OrderDA {

	public static List<Order> viewOrders() {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM orders ORDER BY FIELD(orderStatus, 'pending','shipping', 'completed', 'canceled'), orderDate DESC";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		List<Order> list = new ArrayList<Order>();
		
		try {
			while (set.next())
			{
				int num = set.getInt("orderNumber");
				String stat = set.getString("orderStatus");
				Date date = set.getDate("orderDate");
				Address sAdd = null; // Add getting shipping address
				Address bAdd = null; // add getting billing address
				String pay = set.getString("paymentMethod");
				int conNum = set.getInt("confirmationNumber");
				int userID = set.getInt("userID");
				double total = set.getDouble("orderTotal");
				
				Order order = new Order(num, stat, date, sAdd, bAdd, pay, conNum, userID, total);
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(con);
		return list;
	}

	public static int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE orders SET orderStatus = '" + status + "' WHERE orderNumber = '" + orderID + "'";
		int check = DbAccessImpl.update(con, query);
		return check;
	}

}
