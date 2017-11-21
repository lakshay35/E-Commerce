package object;

import java.util.List;

import persistent.CreditCardDA;
import persistent.CustomerDA;

public class Customer extends User {

	public List<Address> getAddresses(int parseInt) {
		// TODO Auto-generated method stub
		return CustomerDA.getAddresses(parseInt);
	}

	public List<CreditCard> viewCards(int userID) {
		// TODO Auto-generated method stub
		return CreditCardDA.viewCards(userID);
	}

}
