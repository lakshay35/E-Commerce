package object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import persistent.BookDA;
import persistent.CustomerDA;
import persistent.DbAccessImpl;
import persistent.EmailUtility;
import persistent.UserDA;

public class User {
	String fname;
	String lname;
	String email;
	String password;
	int userId;
	String userType;
	int code;
	String status;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User(String first, String last, String email, String pass, int code)
	{
		setFname(first);
		setLname(last);
		setEmail(email);
		setPassword(pass);
		setCode(code);
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int createNewUser() {
		CustomerDA da = new CustomerDA();
		int value = da.createNewCustomer(fname, lname, email, password, code);
		return value;
	}

	public int checkLogin(String email2, String pass) {
		UserDA da = new UserDA();
		int value = 0;
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = da.checkLogin(email2, pass, con);
		try
		{
		if (set.next())
		{
			value = 1;
		}
		else
		{
			value = 0;
		}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		DbAccessImpl.disconnect(con);
		return value;
	}

	public User getUserInfo(String email2, String pass) {
		UserDA da = new UserDA();
		Connection con = (Connection) DbAccessImpl.connect();
		User user = da.getUserInfo(email2, pass, con);
		DbAccessImpl.disconnect(con);
		return user;
	}

	public int verify(String attribute) {
		// TODO Auto-generated method stub
		UserDA da = new UserDA();
		int value = da.verifyAccount(attribute);
		return value;
	}

	public List<Book> browseBooks() {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = BookDA.browseBooks(con);
		List<Book> temp = new ArrayList<Book>();
		if (set != null)
		{
			try {
				while (set.next())
				{
					Book book = new Book(set.getInt("isbn"), set.getString("category"), 
							set.getString("authorName"), set.getString("title"), 
							set.getInt("edition"), set.getString("publisher"), 
							set.getInt("publicationYear"), set.getInt("minThreshold"), 
							set.getInt("qtyInStock"), set.getDouble("buyingPrice"), 
							set.getDouble("sellingPrice"), set.getString("picture"), 
							set.getString("description"));
					temp.add(book);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		else
		{
			return null;
		}
		return temp;
	}

	public int changePassword(String email2, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		UserDA da = new UserDA();
		int value = da.changePassword(email2, oldPassword, newPassword);
		return value;
	}

	public int recoverPassword(String email2, String host, String user2, String port, String pass) {
		// TODO Auto-generated method stub
		String letters = "ABCDE#FGHIJKLab$cdefghMNO%PQ@RSTU&VWXYZ!ijklmnop567qrstuvwxyz1234890";
        StringBuilder sb = new StringBuilder();
        int value;
        for(int i = 0; i < 8; i++) {
            double index = Math.random() * letters.length();
            sb.append(letters.charAt((int)index));
        }
        String newPass = sb.toString();
        
        UserDA da = new UserDA();
        
        try
        {
        	EmailUtility.sendNewPassword(email2, host, user2, pass, port, newPass);
        	value = da.recoverPassword(email2, newPass);
        }
        catch (Exception e)
        {
        	value = 0;
        }
		return value;
	}
}