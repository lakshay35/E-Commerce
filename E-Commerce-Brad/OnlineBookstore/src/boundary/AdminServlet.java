package boundary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.AdminController;
import object.Book;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
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
		String addpromotion = request.getParameter("addpromotion");
		String browse = request.getParameter("browse");
		if (addpromotion != null) 
		{
			addPromotion(request, response);
		}
		if (addbook != null)
		{
			addBook(request, response);
		}
		else if (browse != null)
		{
			browseBooks(request, response);
		}
	}

	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		AdminController aCtrl = new AdminController();
		
		List<Book> bookList = aCtrl.browseBooks();
		root.put("books", bookList);
		for (Book book : bookList)
		{
			book.printBook();
			System.out.println("-----------------------");
		}
		String templateName = "adminBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void addPromotion(HttpServletRequest request, HttpServletResponse response) {
		String promoID = request.getParameter("promoID");
		String name = request.getParameter("promoName");
		String percent = request.getParameter("percentage");
		String expiration = request.getParameter("expiration");
		
		AdminController aCtrl = new AdminController();
		System.out.println(name);
		int check = aCtrl.addPromotion(Integer.parseInt(promoID), name, Double.parseDouble(percent), expiration);
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
