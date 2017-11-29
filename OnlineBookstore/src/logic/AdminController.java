package logic;

import java.util.ArrayList;
import java.util.List;

import entity.IBook;
import object.Book;
import object.Promotion;
import object.SystemAdmin;
import object.User;

public class AdminController {

	public int addNewBook(String title, String author, int edition, String category, int isbn, String publisher, int year,
			int thresh, int quantity, Double buyprice, Double sellprice, String url, String description) {
		IBook newBook = new Book(isbn, category, author, title, edition, publisher, year, thresh, quantity, buyprice, 
				sellprice, url, description);
		System.out.println("In book" + newBook.getThreshold());
		int value = newBook.addBook();
		return value;
	}

	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		List<IBook> returnList = new ArrayList<IBook>();
		SystemAdmin admin = new SystemAdmin();
		returnList = admin.browseBooks();
		return returnList;
	}

	public IBook getBookInfo(int isbn) {
		// TODO Auto-generated method stub
		IBook book = new Book();
		int check = book.getBookInfo(isbn);
		if (check == 1)
		{
			return book;
		}
		else
		{
			return null;
		}
	}

	public int editBook(String title, String author, int edition, String category, int isbn, String publisher, int year,
			int thresh, int quantity, Double buyprice, Double sellprice, String url, String description) {
		IBook newBook = new Book(isbn, category, author, title, edition, publisher, year, thresh, quantity, buyprice, 
				sellprice, url, description);
		int value = newBook.editBook();
		return value;
	}
		
	public int addPromotion(int promoID, String name, double percent, String expiration, String userEmail, String host, String senderPassword, 
			String port) {
		Promotion newPromo = new Promotion(promoID, name, percent, expiration);
		int value = newPromo.addPromo(userEmail, host, senderPassword, port);
		return value;
	}

	public List<User> viewUsers() {
		SystemAdmin admin = new SystemAdmin();
		return admin.viewUsers();
	}

	public int authorizeUser(int userID, int value) {
		// TODO Auto-generated method stub
		SystemAdmin admin = new SystemAdmin();
		return admin.authorizeUser(userID, value);
	}

	public int suspendUser(int userID) {
		// TODO Auto-generated method stub
		SystemAdmin admin = new SystemAdmin();
		return admin.suspendUser(userID);
	}

	public int unsuspendUser(int userID) {
		SystemAdmin admin = new SystemAdmin();
		return admin.unsuspendUser(userID);
	}

	public int checkPromo(int parseInt) {
		// TODO Auto-generated method stub
		Promotion promo = new Promotion();
		return promo.checkPromo(parseInt);
	}

}
