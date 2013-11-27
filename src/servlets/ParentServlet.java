package servlets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;

public class ParentServlet extends HttpServlet{
	
	protected Logger logger = Logger.getRootLogger();
	
    protected void goToPage(String address, HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(address);
        logger.error(address);
        dispatcher.forward(req, resp);
    }

}
