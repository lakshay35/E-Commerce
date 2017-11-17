package logic;

import java.util.ArrayList;
import java.util.List;

import entity.IBook;
import object.Book;
import object.Customer;

public class CustomerController {
	
	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		List<IBook> returnList = new ArrayList<IBook>();
		Customer customer = new Customer();
		returnList = customer.browseBooks();
		return returnList;
	}
	
	public IBook getBookInfo(int isbn) {
		IBook book = new Book();
		book.getBookInfo(isbn);
		return book;
	}
}
