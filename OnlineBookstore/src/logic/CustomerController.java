package logic;

import java.util.ArrayList;
import java.util.List;

import entity.IBook;
import object.Address;
import object.Customer;

public class CustomerController {
	
	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		List<IBook> returnList = new ArrayList<IBook>();
		Customer customer = new Customer();
		returnList = customer.browseBooks();
		return returnList;
	}

	public List<Address> getAddresses(int parseInt) {
		// TODO Auto-generated method stub
		Customer cust = new Customer();
		return cust.getAddresses(parseInt);
	}

	public int addAddress(int userId, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		Address add = new Address(street, city, state, zip);
		return add.addAddress(userId);
	}

	public int editAddress(int id, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		Address add = new Address(street, city, state, zip, id);
		return add.editAddress();
	}

	public int deleteAddress(int id) {
		// TODO Auto-generated method stub
		Address add = new Address();
		add.setId(id);
		return add.deleteAddress();
	}
}
