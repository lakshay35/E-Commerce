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
		
		if (browse != null)
		{
			browseBooks(request, response);
		}
		else if (viewAddress != null)
		{
			viewAddresses(request, response, "");
		}
		else if (addAddress != null)
		{
			addAddress(request, response);
		}
		else if (editAddress != null)
		{
			editAddress(request, response);
		}
		else if (deleteAddress != null)
		{
			deleteAddress(request, response);
		}
		else if (addCard != null)
		{
			addCard(request, response);
		}
		else if (viewCards != null)
		{
			viewCards(request, response, "");
		}
		else if (deleteCard != null)
		{
			deleteCard(request, response);
		}
		else if (searchBooks != null)
		{
			searchBooks(request, response);
		}
	}

	private void searchBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		int temp = Integer.parseInt(request.getParameter("category"));
		String cat = "";
		UserController userCtrl = new UserController();
		
		List<IBook> bookList = new ArrayList<IBook>();
		
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
		
		bookList = userCtrl.searchBooks(cat, term);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("books", bookList);
		root.put("searchTerm", term);
		String templateName = "customerSearch.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void deleteCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("deleteCard"));
		
		CustomerController custCtrl = new CustomerController();
		
		int check = custCtrl.deleteCard(id);
		if (check == 1)
		{
			viewCards(request, response, "Successfully deleted this card.");
		}
		else
		{
			viewCards(request, response, "Failed to delete this card.");
		}
		
	}

	private void viewCards(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		
		int userID = (int)sess.getAttribute("userID");
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		
		CustomerController custCtrl = new CustomerController();
		
		cardList = custCtrl.viewCards(userID);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("cardList", cardList);
		root.put("message", message);
		String templateName = "creditcardPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void addCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String number = request.getParameter("number");
		String type = request.getParameter("type");
		
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
		int check = custCtrl.addCard(number, dateTime, type, userID);
		
		if (check == 1)
		{
			viewCards(request, response, "Successfully added a credit card to your account.");
		}
		else
		{
			viewCards(request, response, "Failed to add the credit card to your account.");
		}
	}

	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("deleteAddress"));
		
		CustomerController custCtrl = new CustomerController();
		
		int check = custCtrl.deleteAddress(id);
		
		if (check == 1)
		{
			viewAddresses(request, response, "An address was deleted.");
		}
		else
		{
			System.out.println("Failure");
		}
	}

	private void editAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("editAddress"));
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		
		CustomerController custCtrl = new CustomerController();
		
		int check = custCtrl.editAddress(id, street, city, state, zip);
		
		if (check >= 1)
		{
			viewAddresses(request, response, "An address has been changed.");
		}
		else
		{
			System.out.println("Failure");
		}
	}

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
			System.out.println("Failure");
		}
		else
		{
			viewAddresses(request, response, "An address has been added.");
		}
	}

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
