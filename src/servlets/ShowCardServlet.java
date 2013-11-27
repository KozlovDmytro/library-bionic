package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CardDao;
import entity.Card;

public class ShowCardServlet extends ParentServlet {

	private CardDao cardDao;

	@Override
	public void init() throws ServletException {
		super.init();
		cardDao = new CardDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Card> cards = cardDao.getAllOnCards();
		req.setAttribute("cards", cards);
		goToPage("/librarian.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
