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
import javax.servlet.http.HttpSession;

import entity.IBook;
import entity.ICart;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.AdminController;
import logic.CustomerController;
import logic.UserController;
import object.Book;
import object.Cart;
import object.Customer;
import persistent.CartDA;

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
		String isbn = request.getParameter("addtocart");
		String getCart = request.getParameter("getCart");
		
		if (browse != null)
		{
			browseBooks(request, response);
		}
		else if(isbn != null)
		{
			int value = addToCart(request,response, isbn);
			if(value == 1) {
				browseBooks(request, response);
			}
		}
		else if(getCart != null) {
			getCart(request,response);
		}
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
	
	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected int addToCart(HttpServletRequest request, HttpServletResponse response, String isbn) throws ServletException, IOException {
		// TODO Auto-generated method stub
		isbn = isbn.replaceAll("[^0-9]", "");
		
		CustomerController custCtrl = new CustomerController();
		IBook book = custCtrl.getBookInfo(Integer.parseInt(isbn));
		HttpSession session = request.getSession(false);
		int userID = 0;
		if (session != null) 
		{
			userID = (int)session.getAttribute("userID");
			System.out.println("userId: " + userID);
		}
		
		int cartID = CartDA.getCartID();
		ICart cart = new Cart(cartID, 0, userID, Integer.parseInt(isbn), 1, book.getSellingPrice());
		return (cart.addToCart());
	}
	
	protected void getCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		int userID = 0;
		if (session != null) 
		{
			userID = (int)session.getAttribute("userID");
			System.out.println("userId: " + userID);
		}
		
		ICart cart1 = new Cart(0, 0, userID, 0, 0, 0);
		List<Cart> cartList = cart1.getCart(userID);
		
		ArrayList<Book> titleList = new ArrayList<Book>();
		double totalPrice = 0.0;
		for (Cart cart : cartList) {
			CustomerController custCtrl = new CustomerController();
			IBook book = custCtrl.getBookInfo(cart.getIsbn());
			titleList.add((Book) book);
		}
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("cart", cartList);
		root.put("book", titleList);
		String templateName = "cartBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}

}
