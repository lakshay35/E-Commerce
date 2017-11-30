package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class TransactionDA {
	
	public static int addToTransaction(int orderNumber,int transactionId,int isbn,int qty,int promoId,double transactionTotal) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.transactions(orderNumber,transactionID,isbn,qty,promoID,total) VALUES ('"+ orderNumber + "', '" + transactionId + "', '" + isbn + "', '" + qty + "', '" + promoId + "', '" + transactionTotal +"')";
		System.out.println(query);
		return DbAccessImpl.create(con, query);
	}
	
	public static int getMaxTrasactionId() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM onlinebookstoredb.transactions where transactionID = (SELECT MAX(transactionID) from onlinebookstoredb.transactions)";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		int value = 0;
		try {
			if (set.next()) {
				value = set.getInt("transactionID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value+1;
	}

}
