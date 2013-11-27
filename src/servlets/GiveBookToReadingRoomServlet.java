package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.ReadingRoomDao;
import entity.Order;

public class GiveBookToReadingRoomServlet extends ParentServlet {

	private OrderDao orderDao;
	private ReadingRoomDao readingRoomDao;

	@Override
	public void init() throws ServletException {
		super.init();
		orderDao = new OrderDao();
		readingRoomDao = new ReadingRoomDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String message;
		Order order = orderDao.getOrderrById(id);
		if (order.getId() == 0) {
			message = "There is no such orders";
		} else {
			readingRoomDao.insertInReadingRoom(order.getBook().getId(), order
					.getUser().getId());
			orderDao.deleteFromOrderById(order.getId());
			message = "The book is in reading room now";
		}
		req.setAttribute("readingRoomMessage", message);
		goToPage("/librarian.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}