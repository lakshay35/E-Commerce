package persistent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
}
