package boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.ShippingController;
import object.Order;

/**
 * Servlet implementation class ShippingServlet
 */
@WebServlet("/ShippingServlet")
public class ShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShippingServlet() {
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
		String orderChange = request.getParameter("orderChange");
		String viewOrders = request.getParameter("viewOrders");
		
		if (viewOrders != null)
		{
			viewOrders(request, response);
		}
		else if (orderChange != null)
		{
			changeOrderStatus(request, response);
		}
	}

	private void viewOrders(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ShippingController sCtrl = new ShippingController();
		
		List<Order> orderList = new ArrayList<Order>();
		
		orderList = sCtrl.viewOrders();
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("orders", orderList);
		String templateName = "viewOrders.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	private void changeOrderStatus(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String status = request.getParameter("status");
		String orderID = request.getParameter("orderChange");
		
		System.out.println("Status: " + status);
		System.out.println("ID: " + orderID);
		
		ShippingController sCtrl = new ShippingController();
		
		int check = sCtrl.changeOrderStatus(orderID, status);
		
		Gson gson = new Gson();
        try {
			response.getWriter().write(gson.toJson(check));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
