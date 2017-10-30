package object;

import java.sql.ResultSet;

import persistent.CustomerDA;
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
		CustomerDA da = new CustomerDA();
		int value = 0;
		ResultSet set = da.checkLogin(email2, pass);
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
		return value;
	}

	public User getUserInfo(String email2, String pass) {
		UserDA da = new UserDA();
		User user = da.getUserInfo(email2, pass);
		return user;
	}

	public int verify(String attribute) {
		// TODO Auto-generated method stub
		UserDA da = new UserDA();
		int value = da.verifyAccount(attribute);
		return value;
	}
}
