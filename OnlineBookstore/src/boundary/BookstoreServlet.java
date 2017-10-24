package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if (signup != null)
		{
			registerUser(request, response);
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
			System.out.println("Success");
			}
			else
			{
				System.out.println("Failure");
			}
		}
		else
		{
			System.out.println("Different Passwords");
		}
	}


}
