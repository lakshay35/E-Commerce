package object;

import persistent.CreditCardDA;

public class CreditCard {
	String number;
	String expirationDate;
	String type;
	String csc;
	int id;
	
	
	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}
	
	public CreditCard (String number, String expire, String type, int id)
	{
		this.setNumber(number);
		this.setExpirationDate(expire);
		this.setType(type);
		this.setId(id);
	}
	
	public CreditCard (String number, String expire, String type)
	{
		this.setNumber(number);
		this.setExpirationDate(expire);
		this.setType(type);
	}
	
	public CreditCard ()
	{
		
	}
	
	public CreditCard(String number2, String expire, String type2, String csc) {
		// TODO Auto-generated constructor stub
		this.setNumber(number2);
		this.setExpirationDate(expire);
		this.setType(type2);
		this.setCsc(csc);
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int addCard(int userID) {
		// TODO Auto-generated method stub
		return CreditCardDA.addCard(userID, number, expirationDate, type, csc);
	}

	public int deleteCard() {
		// TODO Auto-generated method stub
		return CreditCardDA.deleteCard(id);
	}
}
