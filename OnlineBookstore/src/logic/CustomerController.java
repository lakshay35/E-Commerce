package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.IBook;
import object.Address;
import object.Book;
import object.Cart;
import object.CreditCard;
import object.Customer;
import object.Order;
import persistent.CartDA;

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

	public int addCard(String number, String expire, String type, int userID, String csc) {
		// TODO Auto-generated method stub
		CreditCard card = new CreditCard(number, expire, type, csc);
		return card.addCard(userID);
	}

	public List<CreditCard> viewCards(int userID) {
		// TODO Auto-generated method stub
		Customer cust = new Customer();
		return cust.viewCards(userID);
	}

	public int deleteCard(int id) {
		// TODO Auto-generated method stub
		CreditCard card = new CreditCard();
		card.setId(id);
		return card.deleteCard();
	}
	
	public List<Order> viewHistory(int parseInt) {
		Customer cust = new Customer();
		return cust.viewHistory(parseInt);
	}
	
	public IBook getBookInfo(int isbn) {
		IBook book = new Book();
		book.getBookInfo(isbn);
		return book;
	}
	
	public int getMaxOrderNumber() {
		return Order.getMaxOrderNumber();
	}
	
	public Cart getCartByID(int id) {
		Cart cart = new Cart();
		ResultSet set = CartDA.getCartByID(id);
		try {
			while(set.next()) {
				cart.setCartID(id);
				cart.setIsbn(set.getInt("isbn"));
				cart.setPromoID(0);
				cart.setQty(set.getInt("qty"));
				cart.setTotal(set.getDouble("total"));
				cart.setUserID(set.getInt("userID"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return cart;
	}
}
