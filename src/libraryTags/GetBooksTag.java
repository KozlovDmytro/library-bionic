package libraryTags;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;
import entity.Book;

public class GetBooksTag extends TagSupport {
	
	private List<Book> books;

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if (!books.isEmpty()) {

				out.write(" <table><tr><th>ID</th><th>Name</th><th>Author</th>"
						+ "<th>Year</th><th>Availability</th></tr>");

				for (Book book : books) {

					out.write(book.toString() );
				}
				out.write("</table>");
			} else {
				out.write("There is no such book");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
}
