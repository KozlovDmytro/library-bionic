package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CardDao;
import dao.OrderDao;
import dao.ReadingRoomDao;
import entity.Book;
import entity.Order;

public class ReturnBookServlet extends ParentServlet {
	private BookDao bookDao;
	private CardDao cardDao;
	private ReadingRoomDao readingRoomDao;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDao = new BookDao();
		cardDao = new CardDao();
		readingRoomDao = new ReadingRoomDao();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id==""){
				id="0";
		}
		String message;
		Book book = bookDao.getBookById(Integer.parseInt(id));
		if ((!cardDao.isBookPresent(book)) && (!readingRoomDao.isBookPresent(book))) {
			message = "This book is in library or doesn't exist";
		} else {
			bookDao.setBookAvailability(book.getId(), 0);
			
			if (cardDao.isBookPresent(book))
				cardDao.deleteBookFromCard(book);
			else {
				readingRoomDao.deleteBookFromReadingRoom(book);
			}
			message = "The book "+book.getName()+ " now is available in Library ";
		}
		req.setAttribute("returnMessage", message);
		goToPage("/librarian.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
