package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import object.Address;
import object.Order;
import object.ReportEntry;
import object.Transaction;

public class OrderDA {
    
    public static List<Order> viewHistory(int parseInt) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM orders WHERE userID = '" + parseInt + "' ORDER BY orderDate DESC";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		List<Order> list = new ArrayList<Order>();
		try {
			while(set.next())
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
			e.printStackTrace();
		}
		
		int len = list.size();
		for(int i=0; i<len; i++) {
			int oNum = list.get(i).getOrderNumber();
			query = "SELECT * FROM transactions JOIN book ON book.isbn = transactions.isbn WHERE orderNumber = '" + oNum + "'";
			set = DbAccessImpl.retrieve(con, query);
			ArrayList<Transaction> tList = new ArrayList<Transaction>();
			
			try {
				while(set.next()) {
					int isbn = set.getInt("isbn");
					String title = set.getString("title");
					String author = set.getString("authorName");
					int orderNum = set.getInt("orderNumber");
					int qty = set.getInt("qty");
					int promoID = set.getInt("promoID");
					double tTotal = set.getDouble("total");
					
					Transaction transaction = new Transaction(orderNum, isbn, qty, promoID, tTotal, author, title);
					tList.add(transaction);
					System.out.println(title);
				}
				list.get(i).setTransactionList(tList);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		DbAccessImpl.disconnect(con);
		return list;
	}

	public static int addtoOrders(int orderNumber, int agencyID, String orderStatus, String orderDate, String shippingAddress, String billingAddress, String paymentMethod, String confirmationNumber, int userID, double orderTotal) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO orders(orderNumber,agencyID,orderStatus,orderDate,shippingAddress,billingAddress,paymentMethod,confirmationNumber,userID,orderTotal) VALUES (" + "'" + orderNumber + "', '" + agencyID + "', '" + orderStatus + "', '" + orderDate + "','" + shippingAddress + "', '" + billingAddress + "', '" + paymentMethod + "', '" + confirmationNumber + "', '" + userID + "', '" + orderTotal + "')";
		System.out.println(query);
		return DbAccessImpl.create(con, query);
	}
	
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

	public static SimpleHash getSalesReport() {
        Connection con = (Connection) DbAccessImpl.connect();
        Date date = new Date();
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Double total = 0.0;
        String newDate = format.format(date) + " 00:00:00";
        newDate = "2017-11-29 00:00:00";
        
        String newQuery = "SELECT isbn FROM book";
        List<Integer> bookList = new ArrayList<Integer>();
        ResultSet set = DbAccessImpl.retrieve(con, newQuery);
        try {
			while (set.next())
			{
				bookList.add(set.getInt("isbn"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ArrayList<ReportEntry> entries = new ArrayList<ReportEntry>();
        for (int i = 0; i < bookList.size(); i++)
        {
        	String orderQuery = "Select orders.orderNumber, orderTotal, orderDate, book.isbn, qty, total, authorName, title FROM orders JOIN transactions ON transactions.orderNumber = orders.orderNumber JOIN book ON transactions.isbn = book.isbn WHERE orderDate ='" + newDate + "' AND transactions.isbn = '" + bookList.get(i) + "'";
        	ResultSet newSet = DbAccessImpl.retrieve(con, orderQuery);
        	int quantity = 0;
        	double bookTotal = 0.0;
        	int check = 0;
        	Date tempDate = new Date();
        	int tempIsbn = 0;
        	String tempAuthor = "";
        	String tempTitle = "";
        	boolean checkBook = true;
        	try {
				if (newSet.next())
				{
					tempDate = newSet.getDate("orderDate");
					System.out.println(tempDate);
						tempIsbn = newSet.getInt("isbn");
						tempAuthor = newSet.getString("authorName");
						tempTitle = newSet.getString("title");
					quantity += newSet.getInt("qty");
					bookTotal += newSet.getDouble("total");
				}
				else
				{
					checkBook = false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if (checkBook == true)
        	{
        	try {
				while (newSet.next())
				{
					if (check == 0)
					{
						tempDate = newSet.getDate("orderDate");
						System.out.println(tempDate);
						tempIsbn = newSet.getInt("isbn");
						tempAuthor = newSet.getString("authorName");
						tempTitle = newSet.getString("title");
						check++;
					}
					quantity += newSet.getInt("qty");
					bookTotal += newSet.getDouble("total");
				}
				ReportEntry entry = new ReportEntry(tempDate, tempIsbn, quantity, bookTotal, tempAuthor, tempTitle);
				System.out.println(entry.getOrderDate() + " date");
				total += entry.getTotal();
				entries.add(entry);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        }

		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("entries", entries);
		root.put("total", total);
        return root;
    }
	
	public static int getMaxOrderNumber() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM orders where orderNumber = (SELECT MAX(orderNumber) from orders)";
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
