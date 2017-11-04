package boundary;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.AdminController;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String addbook = request.getParameter("addbook");
		
		if (addbook != null)
		{
			addBook(request, response);
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
		
		AdminController aCtrl = new AdminController();
		System.out.println(thresh);
		int check = aCtrl.addNewBook(title, author, Integer.parseInt(edition), category, Integer.parseInt(isbn), publisher, Integer.parseInt(year), 
				Integer.parseInt(thresh), Integer.parseInt(quantity), Double.parseDouble(buyprice), Double.parseDouble(sellprice), url);
		if (check >= 1)
		{
			System.out.println("Success");
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
		/*HttpSession sess = request.getSession(false);
		System.out.println("Hey this is " + sess.getAttribute("fName"));
		System.out.println();
		System.out.println(title);
		System.out.println(author);
		System.out.println(category);
		System.out.println(isbn);
		System.out.println(publisher);
		System.out.println(year);
		System.out.println(thresh);
		System.out.println(quantity);
		System.out.println(buyprice);
		System.out.println(sellprice);
		System.out.println(url);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
