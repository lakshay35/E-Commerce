package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import logic.UserController;
import object.User;

/**
 * Servlet implementation class BookstoreServlet
 */
@WebServlet("/BookstoreServlet")
public class BookstoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BookstoreServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String signup = request.getParameter("signup");
		String login = request.getParameter("login");
		String getName = request.getParameter("name");
		if (signup != null)
		{
			registerUser(request, response);
		}
		else if (login != null)
		{
			loginUser(request, response);
		}
		else if (getName != null)
		{
			retrieveName(request, response);
		}
	}
	
	private void retrieveName(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Gson g = new Gson();
		HttpSession session = request.getSession(false);
		String name = (String)session.getAttribute("fName");
		System.out.println(g.toJson(name));
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		UserController userCtrl = new UserController();
		
		int check = userCtrl.checkLogin(email, pass);
		
		if (check == 1)
		{
			User user = userCtrl.GetUserInfo(email, pass);
			if (user != null)
			{
				HttpSession session = request.getSession();
				synchronized(session) {
					session.setMaxInactiveInterval(-1);
					session.setAttribute("userID", user.getUserId());
					session.setAttribute("fName", user.getFname());
					session.setAttribute("lName", user.getLname());
					session.setAttribute("email", user.getEmail());
					session.setAttribute("userType", user.getUserType());
				}
				if (session.getAttribute("userType").equals("Customer"))
				{
					try {
						response.sendRedirect("Customer.html");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					try {
						response.sendRedirect("login.html");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else
			{
				System.out.println("Error Null");
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("incorrect login");
			try {
				response.sendRedirect("login.html");
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
	

	private void registerUser(HttpServletRequest request, HttpServletResponse response) {
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passConfirmation = request.getParameter("password_confirmation");
		
		UserController userCtrl = new UserController();
		
		if (password.equals(passConfirmation))
		{
			User newUser = new User(fname, lname, email, password);
			int check = userCtrl.CreateNewUser(newUser);
			if (check == 1)
			{
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				try {
					response.sendRedirect("registation.html");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		else
		{
			System.out.println("Different Passwords");
		}
	}


}
