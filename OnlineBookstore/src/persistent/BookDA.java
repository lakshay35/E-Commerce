package persistent;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

public class BookDA {

	public static int addBookToDA(int isbn, String category, String author, String title, int edition, String publisher,
			int year, int quantity, int threshold, String picture, Double buyingPrice, Double sellingPrice, String description) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.book (isbn, category, authorName, title, picture, edition,"
				+ " publisher, publicationYear, qtyInStock, minThreshold, buyingPrice, sellingPrice, description) VALUES"
				+ " ('" + isbn + "', '" + category + "', '" + author + "', '" + title + "', '" + picture + "', '" + edition + "', '" + 
				publisher + "', '" + year + "', '" + quantity + "', '" + threshold + "', '" + buyingPrice + 
				"', '" + sellingPrice + "', '" + description + "')";
		System.out.println(query);
		int value = DbAccessImpl.create(con, query);
		DbAccessImpl.disconnect(con);
		return value;
	}

	public static ResultSet browseBooks(Connection con) {
		// TODO Auto-generated method stub
		String query = "SELECT * from book";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
}
