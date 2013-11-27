package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;
import entity.*;

public class LoginControllerServlet extends ParentServlet {
	
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		super.init();
		userDao = new UserDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String pass = req.getParameter("pass");

		User user = userDao.getUserByLogin(login);
		System.out.println("login= " + login);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("user= " + user);
		if (pass.equals(user.getPass()) && (user.getAccess() == 0)) {
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			goToPage("/librarian.jsp", req, resp);
		} else if (pass.equals(user.getPass()) && (user.getAccess() == 1)) {
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			goToPage("/user.jsp", req, resp);
		} else {
			req.setAttribute("note", "There is no such Login or Password!");
			goToPage("/index.jsp", req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

}
