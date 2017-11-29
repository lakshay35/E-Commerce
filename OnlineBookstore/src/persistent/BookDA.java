package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class BookDA {

	public static int addBookToDA(int isbn, String category, String author, String title, int edition, String publisher,
			int year, int quantity, int threshold, String picture, Double buyingPrice, Double sellingPrice, String description) {
		Boolean checkIsbn = true;
		Connection con = (Connection) DbAccessImpl.connect();
		String checkQuery = "SELECT * FROM book WHERE isbn = '" + isbn + "'";
		ResultSet set = DbAccessImpl.retrieve(con, checkQuery);
		try {
			if (set.next())
			{
				checkIsbn = true;
			}
			else
			{
				checkIsbn = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (checkIsbn == false)
		{
			String query = "INSERT INTO book (isbn, category, authorName, title, picture, edition,"
					+ " publisher, publicationYear, qtyInStock, minThreshold, buyingPrice, sellingPrice, description) VALUES"
					+ " ('" + isbn + "', '" + category + "', '" + author + "', '" + title + "', '" + picture + "', '" + edition + "', '" + 
					publisher + "', '" + year + "', '" + quantity + "', '" + threshold + "', '" + buyingPrice + 
					"', '" + sellingPrice + "', '" + description + "')";
			System.out.println(query);
			DbAccessImpl.disconnect(con);
			return DbAccessImpl.create(con, query);
		}
		else
		{
			DbAccessImpl.disconnect(con);
			return -2;
		}
	}

	public static ResultSet browseBooks(Connection con) {
		// TODO Auto-generated method stub
		String query = "SELECT * from book ORDER BY title ASC";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	public static ResultSet getBookInfo(int isbn, Connection con) {
		String query = "SELECT * from book WHERE isbn = '" + isbn + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}

	public static int editBookDA(int isbn, String category, String author, String title, int edition, String publisher,
			int year, int quantity, int threshold, String picture, Double buyingPrice, Double sellingPrice,
			String description) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE book SET category = '" + category + "', authorName = '" +  author + "', title = '" + title 
				+ "', picture = '" + picture + "', edition = '" + edition + "', publisher = '" + publisher + "', publicationYear = '"
				+ year + "', qtyInStock = '" + quantity + "', minThreshold = '" + threshold + "', buyingPrice = '" + buyingPrice + "',"
				+ " sellingPrice = '" + sellingPrice + "', description = '" + description + "' WHERE isbn = '" + isbn + "'";
		System.out.println(query);
		int value = DbAccessImpl.update(con, query);
		return value;
	}

	public static ResultSet searchBooks(Connection con, String cat, String term) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM book WHERE " + cat + " LIKE '%" + term + "%' ORDER BY " + cat + " ASC";
		System.out.println(query);
		return DbAccessImpl.retrieve(con, query);
	}
	
}
