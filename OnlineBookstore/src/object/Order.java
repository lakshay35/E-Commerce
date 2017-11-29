package object;

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
	
	public Order() {
		
	}
	
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
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		return OrderDA.changeOrderStatus(orderID, status);
	}
	
	
}
