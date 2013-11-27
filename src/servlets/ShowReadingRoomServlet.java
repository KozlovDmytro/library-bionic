package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ReadingRoomDao;
import entity.ReadingRoom;

public class ShowReadingRoomServlet extends ParentServlet {

	private ReadingRoomDao readingRoomDao;

	@Override
	public void init() throws ServletException {
		super.init();
		readingRoomDao = new ReadingRoomDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ReadingRoom> readingRoom = readingRoomDao.getAllInReadingRoom();
		req.setAttribute("readingRoom", readingRoom);
		goToPage("/librarian.jsp", req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
