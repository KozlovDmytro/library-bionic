package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import entity.Book;

public class BookSearchServlet extends ParentServlet {

	private BookDao bookDao;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDao = new BookDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String bookName = req.getParameter("bookName");
		List<Book> books;
		if (bookName.equalsIgnoreCase("all")) {
			books = bookDao.getAllBooks();
		} else {
			books = bookDao.getBookByName(bookName);
		}
		req.setAttribute("books", books);
		goToPage("/user.jsp", req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
