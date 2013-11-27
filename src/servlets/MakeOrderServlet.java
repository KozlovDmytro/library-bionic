package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.BookDao;
import dao.OrderDao;
import dao.UserDao;
import entity.Book;
import entity.User;

public class MakeOrderServlet extends ParentServlet {

	private BookDao bookDao;
	private UserDao userDao;
	private OrderDao orderDao;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDao = new BookDao();
		userDao = new UserDao();
		orderDao = new OrderDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int bookId; 
		if (req.getParameter("bookId") ==""){
			bookId=0;
			}
		
			else {				
			
			bookId=Integer.parseInt(req.getParameter("bookId"));		
		}
		HttpSession session = req.getSession(); 
		User user= userDao.getUserByLogin((session.getAttribute("login")).toString());
		int userId = user.getId();
		String message="";
		Book book= bookDao.getBookById(bookId);
		if (book.getName()!=null){
			switch (book.getAvailability()) {
		case 1:
			message="Sorry this book is not available right now ";
			break;
		case 0:
			 orderDao.insertInOrder(bookId, userId);
			 bookDao.setBookAvailability(bookId, 1);
			message = "Thank you for your order!";
			break;
		default:
			message="";
		}
			}
		req.setAttribute("orderMessage", message);
		goToPage("/user.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
