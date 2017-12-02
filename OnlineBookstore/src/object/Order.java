package object;

/*
	Creates an Order Object
*/
import java.util.Date;

import persistent.OrderDA;

public class Order {
	int orderNumber;
	String orderStatus;
	Date date;
	Address shippingAddress;
	Address billingAddress;
	String paymentMethod;
	int confirmationNumber;
	int userID;
	double orderTotal;

	/*
	Parameters: None
	Return Value: Constructor
	Description: Creates Object
	*/
	public Order() {

	}

	/*
	Parameters: int orderNumber, double orderTotal, Date orderDate
	Return Value: Constructor
	Description: Creates Object
	*/
	public Order(int orderNumber, double orderTotal, Date orderDate) {
        this.setOrderNumber(orderNumber);
        this.setOrderTotal(orderTotal);
        this.setDate(orderDate);
    }

		/*
		Parameters: int num, String stat, Date date, Address sAdd, Address bAdd, String pay, int conNum, int userID, double total
		Return Value: Constructor
		Description: Creates Object
		*/
	public Order(int num, String stat, Date date, Address sAdd, Address bAdd, String pay, int conNum, int userID, double total) {
		this.setOrderNumber(num);
		this.setOrderStatus(stat);
		this.setDate(date);
		this.setShippingAddress(sAdd);
		this.setBillingAddress(bAdd);
		this.setPaymentMethod(pay);
		this.setConfirmationNumber(conNum);
		this.setUserID(userID);
		this.setOrderTotal(total);
	}

	/*
	Parameters: None
	Return Value: int
	Description: Returns orderNumber
	*/
	public int getOrderNumber() {
		return orderNumber;
	}

	/*
	Parameters: int orderNumber
	Return Value: void
	Description: sets orderNumber
	*/
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/*
	Parameters: None
	Return Value: int
	Description: Returns orderStatus
	*/
	public String getOrderStatus() {
		return orderStatus;
	}

	/*
	Parameters: String orderStatus
	Return Value: void
	Description: sets orderStatus
	*/
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/*
	Parameters: None
	Return Value: Date
	Description: Returns date
	*/
	public Date getDate() {
		return date;
	}

	/*
	Parameters: Date date
	Return Value: Date
	Description: sets date
	*/
	public void setDate(Date date) {
		this.date = date;
	}

	/*
	Parameters: None
	Return Value: Address
	Description: Returns shippingAddress
	*/
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/*
	Parameters: Address shippingAddress
	Return Value: void
	Description: sets shippingAddress
	*/
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/*
	Parameters: None
	Return Value: Address
	Description: gets billingAddress
	*/
	public Address getBillingAddress() {
		return billingAddress;
	}

	/*
	Parameters: Address billingAddress
	Return Value: void
	Description: sets billingAddress
	*/
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	/*
	Parameters: None
	Return Value: String
	Description: gets paymentMethod
	*/
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/*
	Parameters: String paymentMethod
	Return Value: void
	Description: sets paymentMethod
	*/
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/*
	Parameters: None
	Return Value: int
	Description: gets confirmationNumber
	*/
	public int getConfirmationNumber() {
		return confirmationNumber;
	}

	/*
	Parameters: int confirmationNumber
	Return Value: void
	Description: sets confirmationnumber
	*/
	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	/*
	Parameters: None
	Return Value: int
	Description: gets userID
	*/
	public int getUserID() {
		return userID;
	}

	/*
	Parameters: int userID)
	Return Value: void
	Description: sets userID
	*/
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/*
	Parameters: None
	Return Value: double
	Description: gets orderTotal
	*/
	public double getOrderTotal() {
		return orderTotal;
	}

	/*
	Parameters: double orderTotal
	Return Value: void
	Description: sets orderTotal
	*/
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	/*
	Parameters: String orderID, String status
	Return Value: int
	Description: changes order status
	*/
	public int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		return OrderDA.changeOrderStatus(orderID, status);
	}


}
