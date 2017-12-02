package object;

/*
	Represents a Customer Object
*/
import java.util.List;

import persistent.CreditCardDA;
import persistent.CustomerDA;

public class Customer extends User {

	/*
	Parameters: int parseInt
	Return Value: List<Address>
	Description: returns user addresses
	*/
	public List<Address> getAddresses(int parseInt) {
		// TODO Auto-generated method stub
		return CustomerDA.getAddresses(parseInt);
	}

	/*
	Parameters: int userID
	Return Value: List<CreditCard>
	Description: returns user credit cards
	*/
	public List<CreditCard> viewCards(int userID) {
		// TODO Auto-generated method stub
		return CreditCardDA.viewCards(userID);
	}

}
