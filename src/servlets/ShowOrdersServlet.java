package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import entity.Order;

public class ShowOrdersServlet extends ParentServlet {
	
	private OrderDao orderDao;

	@Override
	public void init() throws ServletException {
		super.init();
		orderDao = new OrderDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Order> orders = orderDao.getAllOrders();
		req.setAttribute("orders", orders);
		goToPage("/librarian.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
