package boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.IBook;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.AdminController;
import logic.UserController;
import object.User;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
	
	final String host = "smtp.gmail.com";
    final String user = "ecommerce4050@gmail.com";
    final String pass = "ecommercecsci4050";
    final String port = "587";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		String addbook = request.getParameter("addbook");
		String browse = request.getParameter("browse");
		String editbook = request.getParameter("editbook");
		String submitedit = request.getParameter("submitedit");
		String addpromotion = request.getParameter("addpromotion");
		String viewUsers = request.getParameter("viewUsers");
		String authorizeUser = request.getParameter("authorizeUser");
		String suspendUser = request.getParameter("suspendUser");
		String unsuspendUser = request.getParameter("unsuspendUser");
		String searchBooks = request.getParameter("searchBooks");
		
		if (addbook != null)
		{
			addBook(request, response);
		}
		else if (browse != null)
		{
			browseBooks(request, response);
		}
		else if (editbook != null)
		{
			showEditBook(request, response);
		}
		else if (submitedit != null)
		{
			editBook(request, response);
		}
		else if (addpromotion != null) 
		{
			addPromotion(request, response);
		}
		else if (viewUsers != null)
		{
			viewUsers(request, response);
		}
		else if (authorizeUser != null)
		{
			authorizeUser(request, response);
		}
		else if (suspendUser != null)
		{
			suspendUser(request, response);
		}
		else if (unsuspendUser != null)
		{
			unsuspendUser(request, response);
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
	
	private void unsuspendUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("id"));
		
		AdminController aCtrl = new AdminController();
		
		int check = aCtrl.unsuspendUser(userID);
		
		if (check > 0)
		{
			System.out.println("Unsuspended");
			viewUsers(request, response);
		}
		else
		{
			System.out.println("Failure");
		}
	}

	private void suspendUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("id"));
		
		AdminController aCtrl = new AdminController();
		
		int check = aCtrl.suspendUser(userID);
		
		if (check > 0)
		{
			System.out.println("Suspended");
			viewUsers(request, response);
		}
		else
		{
			System.out.println("Failure");
		}
	}

	private void authorizeUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String tempvalue = request.getParameter("authorizeDrop");
		String tempUserID = request.getParameter("id");
		System.out.println(tempvalue);
		System.out.println(tempUserID);
		int value = Integer.parseInt(tempvalue);
		int userID = Integer.parseInt(tempUserID);
		AdminController aCtrl = new AdminController();
		
		int check = aCtrl.authorizeUser(userID, value);
		if (check == -1)
		{
			System.out.println("N/A");
		}
		else if (check == 0)
		{
			System.out.println("Failure");
		}
		else
		{
			System.out.println("Success");
			viewUsers(request, response);
		}
	}

	private void viewUsers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		AdminController aCtrl = new AdminController();
		
		List<User> userList = aCtrl.viewUsers();
		
		root.put("userList", userList);
		String templateName = "viewUsers.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void addPromotion(HttpServletRequest request, HttpServletResponse response) {
		String promoID = request.getParameter("promoID");
		String name = request.getParameter("promoName");
		String percent = request.getParameter("percentage");
		String expiration = request.getParameter("expiration");
		System.out.println(expiration);
		AdminController aCtrl = new AdminController();
		System.out.println(name);
		int check = aCtrl.addPromotion(Integer.parseInt(promoID), name, Double.parseDouble(percent), expiration, user, host, pass, port);
		if(check >= 1) {
			System.out.println("Success");
			try {
				response.sendRedirect("AddPromo.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Failure");
			try {
				response.sendRedirect("AddPromo.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void editBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String isbn = request.getParameter("isbn");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String thresh = request.getParameter("threshold");
		String quantity = request.getParameter("quantity");
		String buyprice = request.getParameter("buyprice");
		String sellprice = request.getParameter("sellprice");
		String edition = request.getParameter("edition");
		String url = request.getParameter("picture");
		String description = request.getParameter("description");
		

		AdminController aCtrl = new AdminController();
		System.out.println(thresh);
		int check = aCtrl.editBook(title, author, Integer.parseInt(edition), category, Integer.parseInt(isbn), publisher, Integer.parseInt(year), 
				Integer.parseInt(thresh), Integer.parseInt(quantity), Double.parseDouble(buyprice), Double.parseDouble(sellprice), url, description);
		
		if (check >= 1)
		{
			System.out.println("Success");
			browseBooks(request, response);
		}
		else
		{
			System.out.println("Failure");
			try {
				response.sendRedirect("AddBook.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void showEditBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int isbn = Integer.parseInt(request.getParameter("editbook"));
		
		AdminController aCtrl = new AdminController();
		
		IBook book = aCtrl.getBookInfo(isbn);
		
		if (book != null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("book", book);
			book.printBook();
			String templateName = "editBook.ftl";
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			System.out.println("No such book exists.");
			browseBooks(request, response);
		}
	}

	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		AdminController aCtrl = new AdminController();
		
		List<IBook> bookList = aCtrl.browseBooks();
		root.put("books", bookList);
		for (IBook book : bookList)
		{
			book.printBook();
			System.out.println("-----------------------");
		}
		String templateName = "adminBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String isbn = request.getParameter("isbn");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String thresh = request.getParameter("threshold");
		String quantity = request.getParameter("quantity");
		String buyprice = request.getParameter("buyprice");
		String sellprice = request.getParameter("sellprice");
		String edition = request.getParameter("edition");
		String url = request.getParameter("picture");
		String description = request.getParameter("description");
		
		AdminController aCtrl = new AdminController();
		System.out.println(thresh);
		int check = aCtrl.addNewBook(title, author, Integer.parseInt(edition), category, Integer.parseInt(isbn), publisher, Integer.parseInt(year), 
				Integer.parseInt(thresh), Integer.parseInt(quantity), Double.parseDouble(buyprice), Double.parseDouble(sellprice), url, description);
		if (check >= 1)
		{
			System.out.println("Success");
			try {
				response.sendRedirect("AddBook.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Failure");
			try {
				response.sendRedirect("AddBook.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
