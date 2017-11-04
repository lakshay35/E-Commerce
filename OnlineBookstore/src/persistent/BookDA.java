package persistent;

import com.mysql.jdbc.Connection;

public class BookDA {

	public static int addBookToDA(int isbn, String category, String author, String title, int edition, String publisher,
			int year, int quantity, int threshold, String picture, Double buyingPrice, Double sellingPrice) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.book (isbn, category, authorName, title, picture, edition,"
				+ " publisher, publicationYear, qtyInStock, minThreshold, buyingPrice, sellingPrice) VALUES"
				+ " ('" + isbn + "', '" + category + "', '" + author + "', '" + title + "', '" + picture + "', '" + edition + "', '" + 
				publisher + "', '" + year + "', '" + quantity + "', '" + threshold + "', '" + buyingPrice + 
				"', '" + sellingPrice + "')";
		System.out.println(query);
		int value = DbAccessImpl.create(con, query);
		DbAccessImpl.disconnect(con);
		return value;
	}
	
}
