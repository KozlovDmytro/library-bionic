package libraryTags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import entity.ReadingRoom;

public class ShowReadingRoomTag extends TagSupport {
	
	private List<ReadingRoom> readingRoom;
	public void setReadingRoom(List<ReadingRoom> readingRoom) {
		this.readingRoom = readingRoom;
	}



	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if ((!readingRoom.isEmpty())) {
				out.write(" <table><tr><th>Id</th><th>User Name</th>"
						+ "<th>Book Name" + "</th><th>Book Id"+"</th></tr>");

				for (ReadingRoom room : readingRoom) {
					out.write(room.toString());
				}
				out.write("</table>");
			} else {
				out.write("There is no people in ReadingRoom");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;

	}

}
