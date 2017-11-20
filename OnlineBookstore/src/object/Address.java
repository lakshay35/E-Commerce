package object;

import persistent.AddressDA;

public class Address {
	String street;
	String state;
	String zip;
	String city;
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address(String street, String city, String state, String zip)
	{
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
	}
	
	public Address(String street, String city, String state, String zip, int id)
	{
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setId(id);
	}
	
	public Address()
	{
		
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public int addAddress(int userId) {
		// TODO Auto-generated method stub
		return AddressDA.addAddress(userId, street, city, state, zip);
	}

	public int editAddress() {
		// TODO Auto-generated method stub
		return AddressDA.editAddress(id, street, city, state, zip);
	}

	public int deleteAddress() {
		// TODO Auto-generated method stub
		return AddressDA.deleteAddress(id);
	}
}
