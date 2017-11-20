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
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.CustomerController;
import object.Address;

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
