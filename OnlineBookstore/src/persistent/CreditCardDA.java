package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

import object.CreditCard;

public class CreditCardDA {

	public static int addCard(int userID, String number, String expirationDate, String type, String csc) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO creditcard (userID, CCnumber, CCtype, expireDate, csc) VALUES ("
				+ "'" + userID + "', '" + number + "', '" + type + "', '" + expirationDate + "', '" + csc + "')";
		return DbAccessImpl.create(con, query);
	}

	public static List<CreditCard> viewCards(int userID) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM creditcard WHERE userID = '" + userID + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		try {
			while (set.next())
			{
				String number = set.getString("CCnumber");
				String sub = number.substring(12, 16);
				number = "XXXX-XXXX-XXXX-" + sub;
				String type = set.getString("CCtype");
				Date expire = set.getDate("expireDate");
				String date = expire.toString();
				int id = set.getInt("CCid");
				CreditCard card = new CreditCard(number, date, type, id);
				cardList.add(card);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(con);
		return cardList;
	}

	public static int deleteCard(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM creditcard WHERE CCid = '" + id + "'";
		Connection con = (Connection) DbAccessImpl.connect();
		return DbAccessImpl.delete(con, query);
	}

}
