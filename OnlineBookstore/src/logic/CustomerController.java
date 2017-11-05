package logic;

import java.util.ArrayList;
import java.util.List;

import object.Book;
import object.Customer;
import object.SystemAdmin;
import object.User;

public class CustomerController {
	
	public List<Book> browseBooks() {
		// TODO Auto-generated method stub
		List<Book> returnList = new ArrayList<Book>();
		User customer = new Customer();
		returnList = customer.browseBooks();
		return returnList;
	}
}
