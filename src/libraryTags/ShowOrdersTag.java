package libraryTags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import entity.Order;

public class ShowOrdersTag extends TagSupport {
	
	private List<Order> orders;	

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if ((!orders.isEmpty())) {
				out.write(" <table><tr><th>Order Id</th><th>User Name</th>"
						+ "<th>Book Name" + "</th><th>Book Id"+"</th></tr>");

				for (Order order : orders) {
					out.write(order.toString());
				}
				out.write("</table>");
			} else {
				out.write("There is no orders");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;

	}
}