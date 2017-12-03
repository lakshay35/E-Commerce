package boundary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.IBook;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.CustomerController;
import logic.UserController;
import object.Address;
import object.CreditCard;

/* Authors: Bradley Reeves, Lakshay Sharma,  Aditya Yadav,  Dhanashree Joshi, Sayed Hussaini   
 * 
 * Description: A servlet used for the Customers that allow them to browse and search books,
 *  add items to their carts, checkout, and other things.
 */

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String browse = request.getParameter("browse");
		String viewAddress = request.getParameter("viewAddress");
		String addAddress = request.getParameter("addAddress");
		String editAddress = request.getParameter("editAddress");
		String deleteAddress = request.getParameter("deleteAddress");
		String addCard = request.getParameter("addCard");
		String viewCards = request.getParameter("viewCreditCard");
		String deleteCard = request.getParameter("deleteCard");
		String searchBooks = request.getParameter("searchBooks");
		
		// Displays a list of books for the Customer
		
		if (browse != null)
		{
			browseBooks(request, response);
		}
		
		// Display a page for the Customer to view, edit, add, and delete addresses.
		
		else if (viewAddress != null)
		{
			viewAddresses(request, response, "");
		}
		
		// Adds an address for the Customer.
		
		else if (addAddress != null)
		{
			addAddress(request, response);
		}
		
		// Edits a Customer's address
		
		else if (editAddress != null)
		{
			editAddress(request, response);
		}
		
		// Deletes a Customer's address
		
		else if (deleteAddress != null)
		{
			deleteAddress(request, response);
		}
		
		// Adds a CreditCard to a customer's account.
		
		else if (addCard != null)
		{
			addCard(request, response);
		}
		
		// Displays a list of all credit cards for a customer.
		
		else if (viewCards != null)
		{
			viewCards(request, response, "");
		}
		
		// Deletes a Customer's credit card.
		
		else if (deleteCard != null)
		{
			deleteCard(request, response);
		}
		
		// Displays a list of books based on a search term.
		
		else if (searchBooks != null)
		{
			searchBooks(request, response);
		}
	}

	// Displays a list of books based on a search term and category.
	
	private void searchBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		int temp = Integer.parseInt(request.getParameter("category"));
		String cat = "";
		UserController userCtrl = new UserController();
		
		List<IBook> bookList = new ArrayList<IBook>();
		
		// Sets the category from the select tag.
		
		if (temp == 0)
		{
			cat = "isbn";
		}
		else if (temp == 1)
		{
			cat = "authorName";
		}
		else if (temp == 2)
		{
			cat = "title";
		}
		else if (temp == 3)
		{
			cat = "category";
		}
		
		// Retrieves a list of books.
		
		bookList = userCtrl.searchBooks(cat, term);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("books", bookList);
		root.put("searchTerm", term);
		String templateName = "customerSearch.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Deletes a Credit card from a user's account.
	
	private void deleteCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if a credit card is selected.
		
		if (!request.getParameter("deleteCard").equals(""))
		{
			int id = Integer.parseInt(request.getParameter("deleteCard"));
			
			CustomerController custCtrl = new CustomerController();
			
			// Deletes the card.
			
			int check = custCtrl.deleteCard(id);
			
			// If it succeeded, reload the page with a success message.
			
			if (check == 1)
			{
				viewCards(request, response, "Successfully deleted this card.");
			}
			
			// Else, reload the page with an error message.
			
			else
			{
				viewCards(request, response, "Failed to delete this card.");
			}
		}
		
		// Reload the page, then display a message telling the user to select a card.
		
		else
		{
			viewCards(request, response, "You must select a card to delete");
		}
	}

	// Displays the page for viewing a user's credit cards.
	
	private void viewCards(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		
		int userID = (int)sess.getAttribute("userID");
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		
		CustomerController custCtrl = new CustomerController();
		
		// Gets the list of a user's credit cards.
		
		cardList = custCtrl.viewCards(userID);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("cardList", cardList);
		root.put("message", message);
		String templateName = "creditcardPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Adds a credit card to a user's account.
	
	private void addCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String number = request.getParameter("number");
		String type = request.getParameter("type");
		String csc = request.getParameter("security");
		
		// Gets the date for the credit card in the correct format.
		
		long timeStamp = Long.parseLong(request.getParameter("expiration"));
		Date date = Date.from( Instant.ofEpochSecond( timeStamp ) );
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		CustomerController custCtrl = new CustomerController();
		
		HttpSession sess = request.getSession(false);
		int userID = (int)sess.getAttribute("userID");
		
		// Adds the card.
		
		int check = custCtrl.addCard(number, dateTime, type, userID, csc);
		
		// Reloads the page with messages depending on if it succeeded in adding a credit card.
		
		if (check == 1)
		{
			viewCards(request, response, "Successfully added a credit card to your account.");
		}
		else
		{
			viewCards(request, response, "Failed to add the credit card to your account.");
		}
	}

	// Deletes an address from a user's account.
	
	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if an address is selected.
		
		if (!request.getParameter("deleteAddress").equals(""))
		{
			int id = Integer.parseInt(request.getParameter("deleteAddress"));
			
			CustomerController custCtrl = new CustomerController();
			
			int check = custCtrl.deleteAddress(id);
			
			// Prints out a message for the user.
			
			if (check == 1)
			{
				viewAddresses(request, response, "An address was deleted.");
			}
			else
			{
				viewAddresses(request, response, "Failed to delete an address.");
			}
		}
		else
		{
			viewAddresses(request, response, "You must select an address to delete");
		}
	}

	// Edits a selected address.
	
	private void editAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if an address is selected.
		
		if (!request.getParameter("editAddress").equals(""))
		{
			int id = Integer.parseInt(request.getParameter("editAddress"));
			
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			
			CustomerController custCtrl = new CustomerController();
			
			// Updates the address in the database.
			
			int check = custCtrl.editAddress(id, street, city, state, zip);
			
			// Prints out messages for the user.
			
			if (check >= 1)
			{
				viewAddresses(request, response, "An address has been changed.");
			}
			else
			{
				viewAddresses(request, response, "Failed to change an address.");
			}
		}
		else
		{
			viewAddresses(request, response, "You must first select an address to edit it.");
		}
	}

	// Adds an address to the database.
	
	private void addAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		
		CustomerController custCtrl = new CustomerController();
		HttpSession sess = request.getSession(false);
		int userId = (int)sess.getAttribute("userID");
		
		int check = custCtrl.addAddress(userId, street, city, state, zip);
		
		if (check == 0)
		{
			viewAddresses(request, response, "Failed to add an address");
		}
		else
		{
			viewAddresses(request, response, "An address has been added.");
		}
	}

	// Displays a page for viewing, adding, editing, and deleting addresses.
	
	private void viewAddresses(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		CustomerController custCtrl = new CustomerController();
		
		List<Address> addressList = new ArrayList<Address>();
		
		HttpSession sess = request.getSession(false);
		String id = sess.getAttribute("userID").toString();
		addressList = custCtrl.getAddresses(Integer.parseInt(id));
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("addressList", addressList);
		root.put("message", message);
		String templateName = "addressPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Displays a list of books for the Customer to browse.
	
	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		CustomerController custCtrl = new CustomerController();
		
		List<IBook> bookList = custCtrl.browseBooks();
		root.put("books", bookList);
		String templateName = "customerBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
