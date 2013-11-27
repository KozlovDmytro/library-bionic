package entity;


public class Book {
	private int id;
	private String name;
	private String author;
	private int year;
	private int availability;

	public int getId() {
		return id;
	}

	public int getAvailability() {
		return availability;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public void setId(int BookId) {
		id = BookId;
	}

	public void setName(String BookName) {
		name = BookName;
	}

	public void setAuthor(String BookAuthor) {
		author = BookAuthor;
	}

	public void setYear(int BookYear) {
		year = BookYear;
	}

	public void setAvailability(int BookAvailability) {
		availability = BookAvailability;
	}

	@Override
	public String toString() {
		String available = "";
		if (getAvailability() == 0) {
			available = "available";
		} else
			available = "not available";

		return "<tr><td>"+ getId()+ "</td>" + "<td> " + getName()+"</td>"
		+ "<td>" + getAuthor()+"</td>" + "<td>"+ getYear()+"</td>" 
		+ "<td>" + available+ "</td></tr>";
	}
}
