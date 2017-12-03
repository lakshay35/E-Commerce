package logic;

import java.util.List;

import object.Order;
import persistent.OrderDA;

public class ShippingController {

	public List<Order> viewOrders() {
		// TODO Auto-generated method stub
		return OrderDA.viewOrders();
	}

	public int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		Order order = new Order();
		return order.changeOrderStatus(orderID, status);
	}

}
