package logic;

import java.util.ArrayList;
import java.util.List;
import object.Book;
import object.Promotion;
import object.SystemAdmin;
import object.User;

public class AdminController {

	public int addNewBook(String title, String author, int edition, String category, int isbn, String publisher, int year,
			int thresh, int quantity, Double buyprice, Double sellprice, String url, String description) {
		Book newBook = new Book(isbn, category, author, title, edition, publisher, year, thresh, quantity, buyprice, 
				sellprice, url, description);
		System.out.println("In book" + newBook.getThreshold());
		int value = newBook.addBook();
		return value;
	}
	
	public int addPromotion(int promoID, String name, double percent, String expiration) {
		Promotion newPromo = new Promotion(promoID, name, percent, expiration);
		int value = newPromo.addPromo();
		return value;
	}

	public List<Book> browseBooks() {
		// TODO Auto-generated method stub
		List<Book> returnList = new ArrayList<Book>();
		User admin = new SystemAdmin();
		returnList = admin.browseBooks();
		return returnList;
	}


}
