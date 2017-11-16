package object;

public class UserProfile {

  String fname;
  String lname;
  String phone;
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

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

String email;

  public UserProfile(String fname, String lname, String phone, String email) {
    this.fname = fname;
    this.lname = lname;
    this.phone = phone;
    this.email = email;
  }

}
