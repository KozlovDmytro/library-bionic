package entity;

public class Order {

	private int id;
	private Book book;
	private User user;

	public int getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public User getUser() {
		return user;
	}

	public void setId(int orderId) {
		id = orderId;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "<tr><td>" + getId() + "</td><td>" + user.getLogin()
				+ "</td><td>" + book.getName() + "</td><td>" + book.getId()
				+ "</td></tr>";
	}
}
