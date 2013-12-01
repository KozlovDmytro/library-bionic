package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import entity.Order;

public class ShowUserOrdersServlet extends ParentServlet {
	
	private OrderDao orderDao;

	@Override
	public void init() throws ServletException {
		super.init();
		orderDao = new OrderDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userName = session.getAttribute("login").toString();
		List<Order> orders = orderDao.getOrdersByUserName(userName);
		req.setAttribute("orders", orders);
		goToPage("/user.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

} 


