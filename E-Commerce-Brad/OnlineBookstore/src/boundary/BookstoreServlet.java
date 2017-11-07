package boundary;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import logic.UserController;
import object.User;
import persistent.DbAccessImpl;
import persistent.EmailUtility;

/**
 * Servlet implementation class BookstoreServlet
 */
@WebServlet("/BookstoreServlet")
public class BookstoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    final String host = "smtp.gmail.com";
    final String user = "ecommerce4050@gmail.com";
    final String pass = "ecommercecsci4050";
    final String port = "587";

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
		String verify = request.getParameter("verify");
		String newPass = request.getParameter("newPassword");
		String forgotPass = request.getParameter("forgotPass");
		
		if (signup != null)
		{
			try {
				registerUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (login != null)
		{
			System.out.println("Gets Request.");
			loginUser(request, response);
		}
		else if (getName != null)
		{
			retrieveName(request, response);
		}
		else if (verify != null)
		{
			verifyAccount(request, response);
		}
		else if (newPass != null)
		{
			changePassword(request, response);
		}
		else if (forgotPass != null)
		{
			recoverPassword(request, response);
		}
	}
	
	private void recoverPassword(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		UserController userCtrl = new UserController();
		int check = userCtrl.recoverPassword(email, host, user, port, pass);
		if (check == 0)
		{
			System.out.println("error");
		}
		else
		{
			System.out.println("Success");
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        HttpSession session = request.getSession(false);
        
        UserController userCtrl = new UserController();
        int check = userCtrl.changePassword((String)session.getAttribute("email"), oldPassword, newPassword);
        if (check == 0)
        {
        	System.out.println("Failed to change password");
        }
        else
        {
        	System.out.println("Success");
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	private void verifyAccount(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		HttpSession session = request.getSession(false);
		UserController userCtrl = new UserController();
		
		if (code.equals(session.getAttribute("userCode").toString()))
		{
			int check = userCtrl.verifyAccount((String)session.getAttribute("email"));
			if (check == 1)
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
					response.sendRedirect("verifycode.html");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				response.sendRedirect("verifycode.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
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
					session.setAttribute("userCode", user.getCode());
					session.setAttribute("status", user.getStatus());
					session.setAttribute("loggedin", "false");
				}
				String stat = (String)session.getAttribute("status");
				if (stat.equals("verified"))
				{
					session.setAttribute("loggedin", "true");
					if (session.getAttribute("userType").equals("Customer"))
					{
						try {
							response.sendRedirect("Customer.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if (session.getAttribute("userType").equals("SystemAdmin"))
					{
						try {
							response.sendRedirect("Admin.html");
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
				else if (stat.equals("unverified"))
				{
					try {
						response.sendRedirect("verifycode.html");
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private int createCode()
	{
		int value;
		do
		{
			value = (int)Math.floor(Math.random()*9999 + 1000);
		}
		while (!(value >= 1000 && value <= 9999));
		return value;
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passConfirmation = request.getParameter("password_confirmation");
		boolean emailDoesExist = false;
		UserController userCtrl = new UserController();

		
		//check if email already exists in DB
	
		Connection conn = (Connection) DbAccessImpl.connect();
		String query = "SELECT COUNT(email) FROM user WHERE email=\"" + email + "\";";
		int setCount = 0;
        ResultSet set = DbAccessImpl.retrieve(conn, query);
        while (set.next()) {
            setCount = Integer.parseInt(set.getString(1));
        }
        if(setCount != 0) {
        		emailDoesExist = true;
        }
        
		if (password.equals(passConfirmation) && (emailDoesExist==false))
		{
			int code = createCode();
			User newUser = new User(fname, lname, email, password, code);
			int check = userCtrl.CreateNewUser(newUser);
			if (check == 1)
			{

		        try {
		            EmailUtility.sendConfirmation(newUser.getEmail(), host, user, pass, port, code);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

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
		else if (emailDoesExist) {
			System.out.println("Email already exists");
		}
		else
		{
			System.out.println("Different Passwords");
		}
	}


}
