package object;

import persistent.CustomerDA;

public class User {
	String fname;
	String lname;
	String email;
	String password;
	
	public User(String first, String last, String email, String pass)
	{
		setFname(first);
		setLname(last);
		setEmail(email);
		setPassword(pass);
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
		int value = da.createNewCustomer(fname, lname, email, password);
		return value;
	}
}
