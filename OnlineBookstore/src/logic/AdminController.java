package logic;

import object.Book;

public class AdminController {

	public int addNewBook(String title, String author, int edition, String category, int isbn, String publisher, int year,
			int thresh, int quantity, Double buyprice, Double sellprice, String url) {
		Book newBook = new Book(isbn, category, author, title, edition, publisher, year, thresh, quantity, buyprice, 
				sellprice, url);
		System.out.println("In book" + newBook.getThreshold());
		int value = newBook.addBook();
		return value;
	}

}
