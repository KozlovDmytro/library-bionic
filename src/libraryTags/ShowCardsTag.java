package libraryTags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import entity.Card;

public class ShowCardsTag extends TagSupport {
	
	private List<Card> cards;
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if ((!cards.isEmpty())) {
				out.write(" <table><tr><th>Id</th><th>User Name</th>"
						+ "<th>Book Name" + "</th><th>Book Id"+"</th></tr>");

				for (Card card : cards) {
					out.write(card.toString());
				}
				out.write("</table>");
			} else {
				out.write("There is no books on Cards");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;

	}
}
