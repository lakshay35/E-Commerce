package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class PromotionDA {

	public static int addPromoToDA(int promoID, String name, double percent, String expiration) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.promotion (promoID, pName, percentage, expiration) VALUES"
				+ " ('" + promoID + "', '" + name + "', '" + percent + "', '" + expiration + "')";
		System.out.println(query);
		int value = DbAccessImpl.create(con, query);
		DbAccessImpl.disconnect(con);
		return value;
	}
	
	public static ArrayList<String> getEmailList() {
        ArrayList<String> list = new ArrayList<String>();
        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT email FROM user WHERE userType = 'Customer'";
        ResultSet rs = DbAccessImpl.retrieve(con, query);
        try {
			while(rs.next()) {
			    list.add(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DbAccessImpl.disconnect(con);
        return list;
    }
}
