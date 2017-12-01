package boundary;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.IBook;
import entity.ICart;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModel;
import logic.CustomerController;
import logic.UserController;
import object.Book;
import object.Cart;
import persistent.AddressDA;
import persistent.CartDA;
import persistent.CreditCardDA;
import persistent.EmailUtility;
import persistent.OrdersDA;
import persistent.PromotionDA;
import persistent.TransactionDA;
import object.Address;
import object.CreditCard;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor process;
	
	final String host = "smtp.gmail.com";
    final String user = "ecommerce4050@gmail.com";
    final String pass = "ecommercecsci4050";
    final String port = "587";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String browse = request.getParameter("browse");
		String viewAddress = request.getParameter("viewAddress");
		String addAddress = request.getParameter("addAddress");
		String editAddress = request.getParameter("editAddress");
		String deleteAddress = request.getParameter("deleteAddress");
		String addCard = request.getParameter("addCard");
		String viewCards = request.getParameter("viewCreditCard");
		String deleteCard = request.getParameter("deleteCard");
		String searchBooks = request.getParameter("searchBooks");
		String isbn = request.getParameter("addtocart");
		String applyPromo = request.getParameter("applyPromo");
		String getCart = request.getParameter("getCart");
		String updateCart = request.getParameter("updateItem");
		String deleteFromCart = request.getParameter("deleteItem");
		String checkOut = request.getParameter("checkoutCart");
		String continueToCheckOut = request.getParameter("continueToCheckOut");
		String completePurchase = request.getParameter("completePurchase");
		
		if (browse != null)
		{
			browseBooks(request, response);
		}
		else if (viewAddress != null)
		{
			viewAddresses(request, response, "");
		}
		else if (addAddress != null)
		{
			addAddress(request, response);
		}
		else if (editAddress != null)
		{
			editAddress(request, response);
		}
		else if (deleteAddress != null)
		{
			deleteAddress(request, response);
		}
		else if (addCard != null)
		{
			addCard(request, response);
		}
		else if (viewCards != null)
		{
			viewCards(request, response, "");
		}
		else if (deleteCard != null)
		{
			deleteCard(request, response);
		}
		else if (searchBooks != null)
		{
			searchBooks(request, response);
		}
		else if(isbn != null)
		{
			int value = addToCart(request,response, isbn);
			if(value == 1) {
				browseBooks(request, response);
			}else {
				browseBooks(request, response);
			}
		}
		else if(applyPromo != null)
		{
			showCart(request, response);
		}
		else if(getCart != null) {
			showCart(request, response);
		}
		else if(updateCart != null) {
			updateCart(request, response, updateCart);
		}
		else if(deleteFromCart != null) {
			deleteFromCart(request, response, deleteFromCart);
		}
		else if(checkOut != null) {
			showAddressAndPayment(request, response);
		}
		else if(continueToCheckOut != null) {
			continueToCheckOut(request,response);
		}
		else if(completePurchase != null) {
			completePurchase(request,response);
		}
	}

	private void searchBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		int temp = Integer.parseInt(request.getParameter("category"));
		String cat = "";
		UserController userCtrl = new UserController();
		
		List<IBook> bookList = new ArrayList<IBook>();
		
		if (temp == 0)
		{
			cat = "isbn";
		}
		else if (temp == 1)
		{
			cat = "authorName";
		}
		else if (temp == 2)
		{
			cat = "title";
		}
		
		bookList = userCtrl.searchBooks(cat, term);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("books", bookList);
		root.put("searchTerm", term);
		String templateName = "customerSearch.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void deleteCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("deleteCard"));
		
		CustomerController custCtrl = new CustomerController();
		
		int check = custCtrl.deleteCard(id);
		if (check == 1)
		{
			viewCards(request, response, "Successfully deleted this card.");
		}
		else
		{
			viewCards(request, response, "Failed to delete this card.");
		}
		
	}

	private void viewCards(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		
		int userID = getUserId(request);
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		
		CustomerController custCtrl = new CustomerController();
		
		cardList = custCtrl.viewCards(userID);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("cardList", cardList);
		root.put("message", message);
		String templateName = "creditcardPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void addCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String number = request.getParameter("number");
		String type = request.getParameter("type");
		String ccID = request.getParameter("ccID");
		if (ccID == null) {ccID = "000";}
		long timeStamp = Long.parseLong(request.getParameter("expiration"));
		Date date = Date.from( Instant.ofEpochSecond( timeStamp ) );
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		CustomerController custCtrl = new CustomerController();
		
		int userID = getUserId(request);
		int check = custCtrl.addCard(number, dateTime, type, userID,Integer.parseInt(ccID));
		
		if (check == 1)
		{
			viewCards(request, response, "Successfully added a credit card to your account.");
		}
		else
		{
			viewCards(request, response, "Failed to add the credit card to your account.");
		}
	}

	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("deleteAddress"));
		
		CustomerController custCtrl = new CustomerController();
		
		int check = custCtrl.deleteAddress(id);
		
		if (check == 1)
		{
			viewAddresses(request, response, "An address was deleted.");
		}
		else
		{
			System.out.println("Failure");
		}
	}

	private void editAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("editAddress"));
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		
		CustomerController custCtrl = new CustomerController();
		
		int check = custCtrl.editAddress(id, street, city, state, zip);
		
		if (check >= 1)
		{
			viewAddresses(request, response, "An address has been changed.");
		}
		else
		{
			System.out.println("Failure");
		}
	}

	private void addAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		
		CustomerController custCtrl = new CustomerController();
		int userId = getUserId(request);
		
		int check = custCtrl.addAddress(userId, street, city, state, zip);
		
		if (check == 0)
		{
			System.out.println("Failure");
		}
		else
		{
			viewAddresses(request, response, "An address has been added.");
		}
	}

	private void viewAddresses(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		CustomerController custCtrl = new CustomerController();
		
		List<Address> addressList = new ArrayList<Address>();
		
		int id = getUserId(request);
		addressList = custCtrl.getAddresses(id);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("addressList", addressList);
		root.put("message", message);
		String templateName = "addressPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		CustomerController custCtrl = new CustomerController();
		
		List<IBook> bookList = custCtrl.browseBooks();
		root.put("books", bookList);
		String templateName = "customerBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected int addToCart(HttpServletRequest request, HttpServletResponse response, String isbn) throws ServletException, IOException {
		// TODO Auto-generated method stub
		isbn = isbn.replaceAll("[^0-9]", "");
		
		CustomerController custCtrl = new CustomerController();
		IBook book = custCtrl.getBookInfo(Integer.parseInt(isbn));
		if(book.getQuantity() > 0) {
			int userID = getUserId(request);
			int cartID = CartDA.getCartID();
			ICart cart = new Cart(cartID, userID, 0, Integer.parseInt(isbn), 1, book.getSellingPrice());
			IBook newBook = new Book(book.getIsbn(), book.getCategory(), book.getAuthor(), book.getTitle(), book.getEdition(), book.getPublisher(), book.getYear(), book.getThreshold(), book.getQuantity()-1, book.getBuyingPrice(), book.getSellingPrice(), book.getPicture(), book.getDescription());
			@SuppressWarnings("unused")
			int newValue = newBook.editBook();
			return (cart.addToCart());
		} else {
			return 0;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	protected SimpleHash getCart(HttpServletRequest request, HttpServletResponse response) {
		int userID = getUserId(request);
		
		ICart cart1 = new Cart(0, 0, userID, 0, 0, 0);
		List<Cart> cartList = cart1.getCart(userID);
		
		ArrayList<Book> titleList = new ArrayList<Book>();
		double sum = 0.0;
		for (Cart cart : cartList) {
			sum = sum + cart.getTotal();
			CustomerController custCtrl = new CustomerController();
			IBook book = custCtrl.getBookInfo(cart.getIsbn());
			titleList.add((Book) book);
		}
		
		String promoCode = request.getParameter("promoCode");
		if(promoCode != null) {promoCode = promoCode.replaceAll("[^0-9]", "");}
		ArrayList<String> promoCodeList = new ArrayList<String>();
		if(promoCode == null || promoCode.equals("")) {
			promoCodeList.add("Enter Promo Code");
		} else {
			int promo = 0;
			if(promoCode != "") {
				promo = Integer.parseInt(promoCode);
			}
			ResultSet set = PromotionDA.getPromotion(promo);
			try {
				if (set.next()) {
					String expirationDate = set.getString("expiration");
					try {
						Date expiry = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
						Date currentDate = new Date();
						if(expiry.compareTo(currentDate) < 0) {
							promoCodeList.add("PROMO CODE INVALID");
						} else {
							double percent = Double.parseDouble(set.getString("percentage"));
							double discount = (sum*percent)/100;
							sum = sum - discount;
							promoCodeList.add(promoCode);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					promoCodeList.add("PROMO CODE INVALID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<String> cartTotal = new ArrayList<String>();
		cartTotal.add(Double.toString(sum));
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("cart", cartList);
		root.put("book", titleList);
		root.put("total", cartTotal);
		root.put("promo", promoCodeList);
		
		return root;
	}
	
	protected void showCart(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root = getCart(request,response);
		process.processTemplate("cartBrowse.ftl", root, request, response);
	}
	
	protected void updateCart(HttpServletRequest request, HttpServletResponse response, String updateCart) {
		int isbn = Integer.parseInt(updateCart.replaceAll("[^0-9]", ""));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int cartId = Integer.parseInt(request.getParameter("cartID"));
		int userID = getUserId(request);
		
		CustomerController custCtrl = new CustomerController();
		IBook book = custCtrl.getBookInfo(isbn);
		
		if(book.getQuantity() < quantity) {
			double quant = book.getSellingPrice()*quantity;
			
			ICart cart = new Cart(cartId, userID, 0, isbn, quantity, quant);
			
			int value = 0;
			value = cart.updateCart();
			if(value != 0) {
				System.out.println("Cart Updated");
				showCart(request, response);
			}
		} else {
			System.out.println("Cart wasn't updated since item not in stock");
			showCart(request, response);
		}
	}
	
	protected void deleteFromCart(HttpServletRequest request, HttpServletResponse response, String updateCart) {
		int isbn = Integer.parseInt(updateCart.replaceAll("[^0-9]", ""));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int cartId = Integer.parseInt(request.getParameter("cartID"));
		int userID = getUserId(request);
		
		CustomerController custCtrl = new CustomerController();
		IBook book = custCtrl.getBookInfo(isbn);
		
		ICart cart = new Cart(cartId, userID, 0, isbn, 0, 0);
		
		IBook newBook = new Book(book.getIsbn(), book.getCategory(), book.getAuthor(), book.getTitle(), book.getEdition(), book.getPublisher(), book.getYear(), book.getThreshold(), book.getQuantity()+quantity, book.getBuyingPrice(), book.getSellingPrice(), book.getPicture(), book.getDescription());
		@SuppressWarnings("unused")
		int newValue = newBook.editBook();
		
		int value = 0;
		
		value = cart.deleteFromCart();
		
		if(value != 0) {
			System.out.println("Cart Updated");
			showCart(request, response);
		}
	}

	protected void showAddressAndPayment(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root = getAddressAndPayment(request,response);
		process.processTemplate("addressAndPayment.ftl", root, request, response);
	}
	
	protected SimpleHash getAddressAndPayment(HttpServletRequest request, HttpServletResponse response) {
		int userID = getUserId(request);
		CustomerController custCtrl = new CustomerController();
		
		ArrayList<Address> addressList = new ArrayList<Address>();
		addressList = (ArrayList<Address>) custCtrl.getAddresses(userID);
		
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		cardList = custCtrl.viewCards(userID);
		
		ArrayList<String> orderTotalList = new ArrayList<String>();
		orderTotalList.add(request.getParameter("orderTotal"));
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("address", addressList);
		root.put("card", cardList);
		root.put("total", orderTotalList);
		
		return root;
	}
	
	private void continueToCheckOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String selectedShippingAddress = request.getParameter("selectShipAddress");
		String selectedBillingAddress = request.getParameter("selectBillAddress");
		String selectedCard = request.getParameter("selectCard");
		
		Address address = new Address();
		if(selectedShippingAddress.equals("newAddress"))
		{
			address.setStreet(request.getParameter("newshipstreet"));
			address.setCity(request.getParameter("newshipcity"));
			address.setState(request.getParameter("newshipstate"));
			address.setZip(request.getParameter("newshipzip"));
			CustomerController custCtrl = new CustomerController();
			custCtrl.addAddress(this.getUserId(request), request.getParameter("newshipstreet"), request.getParameter("newshipcity"), request.getParameter("newshipstate"), request.getParameter("newshipzip"));
		}
		else
		{
			address = Address.getAddressById(Integer.parseInt(selectedShippingAddress));
		}
		ArrayList<Address> shippingAddress = new ArrayList<Address>();
		shippingAddress.add(address);
		
		if(selectedBillingAddress.equals("newAddress"))
		{
			address.setStreet(request.getParameter("newbillstreet"));
			address.setCity(request.getParameter("newbillcity"));
			address.setState(request.getParameter("newbillstate"));
			address.setZip(request.getParameter("newbillzip"));
		}
		else
		{
			address = Address.getAddressById(Integer.parseInt(selectedBillingAddress));
		}
		ArrayList<Address> billingAddress = new ArrayList<Address>();
		billingAddress.add(address);
		
		CreditCard card= new CreditCard();
		if(selectedCard.equals("newCard"))
		{
			card.setNumber(request.getParameter("newcardnumber"));
			card.setType(request.getParameter("newcardtype"));
			card.setExpirationDate(request.getParameter("newcardexpiration"));
			card.setId(Integer.parseInt(request.getParameter("newccid")));
			CustomerController addCard = new CustomerController();
			addCard.addCard(request.getParameter("newcardnumber"), request.getParameter("newcardexpiration"), request.getParameter("newcardtype"), getUserId(request), Integer.parseInt(request.getParameter("newccid")));
		}
		else
		{
			card = CreditCard.getCreditCardById(Integer.parseInt(selectedCard));
		}
		ArrayList<CreditCard> billingCard = new ArrayList<CreditCard>();
		billingCard.add(card);
		
		ArrayList<String> orderTotalList = new ArrayList<String>();
		orderTotalList.add(request.getParameter("orderTotal"));
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root = getCart(request,response);
		root.put("shippingAddress", shippingAddress);
		root.put("billingAddress", billingAddress);
		root.put("billingCard", billingCard);
		root.put("orderTotal", orderTotalList);
		process.processTemplate("completePurchase.ftl", root, request, response);
	}
		
	private void completePurchase(HttpServletRequest request, HttpServletResponse response) {
		//Get MAX order numer
		CustomerController custCtrl = new CustomerController();
		int orderNumber = custCtrl.getMaxOrderNumber();
		ArrayList<String> orderNumberList = new ArrayList<String>();
		orderNumberList.add(Integer.toString(orderNumber));
		
		//Compare credit card with valid card. Set order status to completed
		String card = request.getParameter("billCard");
		boolean isValidCard = true;
		String status = "pending";
		if(isValidCard) {status = "completed";}
		
		//Set today's date as order date
		Date date = new Date();
		String orderDate = Integer.toString(1900+date.getYear()) + '-' + Integer.toString(date.getMonth()+1) + '-' + Integer.toString(date.getDate());
		ArrayList<String> orderDateList = new ArrayList<String>();
		orderDateList.add(orderDate);
		
		//shipping address
		String shippingAddress = request.getParameter("shipAddress");
		ArrayList<String> shippingAddressList = new ArrayList<String>();
		shippingAddressList.add(shippingAddress);
		
		//billing address
		String billingAddress = request.getParameter("billAddress");
		ArrayList<String> billingAddressList = new ArrayList<String>();
		billingAddressList.add(billingAddress);
		
		//Set payment method as type and last four digits
		String[] splitCard = card.split(",");
		String paymentMethod = splitCard[0] + " " + splitCard[2].substring(splitCard[2].length() - 4);
		ArrayList<String> paymentMethodList = new ArrayList<String>();
		paymentMethodList.add(paymentMethod);
		
		//set MAX confirmation Number
		String confirmationNumber = "Conf" + orderNumber;
		ArrayList<String> confirmationNumberList = new ArrayList<String>();
		confirmationNumberList.add(confirmationNumber);
		
		//set user id
		int userId = getUserId(request);
		
		//set total
		String orderTotal = request.getParameter("orderTotal");
		double total = Double.parseDouble(orderTotal);
		
		int success = OrdersDA.addtoOrders(orderNumber, 0, status, orderDate, shippingAddress, billingAddress, paymentMethod, confirmationNumber, userId, total);
		
		if(success != 0) {
			ICart cart1 = new Cart(0, 0, userId, 0, 0, 0);
			List<Cart> cartList = cart1.getCart(userId);
			for(ICart cart:cartList) {
				int transactionId = TransactionDA.getMaxTrasactionId();
				int isbn = cart.getIsbn();
				int qty = cart.getQty();
				int promoId = cart.getPromoID();
				double transactionTotal = cart.getTotal();
				success = TransactionDA.addToTransaction(orderNumber,transactionId,isbn,qty,promoId,transactionTotal);
			}
		}
		
		if(success != 0) {
			CartDA.deleteCartItems(userId);
		}
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("orderNumber",orderNumberList);
		root.put("shipAddress",shippingAddressList);
		root.put("billAddress", billingAddressList);
		root.put("paymentMethod", paymentMethodList);
		root.put("confNumber", confirmationNumberList);
		
		try {
			EmailUtility.sendOrderConfirmation((String)request.getSession(false).getAttribute("email"), host, port, user, pass, orderNumberList, shippingAddressList, billingAddressList, paymentMethodList, confirmationNumberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		process.processTemplate("confirmationPurchase.ftl", root, request, response);
	}

	public int getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int userID = 0;
		if (session != null) 
		{
			userID = (int)session.getAttribute("userID");
		}
		return userID;
	}
}
