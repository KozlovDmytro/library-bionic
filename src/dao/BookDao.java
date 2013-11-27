package dao;

import entity.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {


	private PreparedStatement ps;
	private Connection c;

	// protected Logger logger = Logger.getRootLogger();

	public void setBookAvailability(int bookId, int bookAvailability) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("UPDATE Library.Book SET availability=? "
					+ "WHERE id=?;");
			ps.setInt(2, bookId);
			ps.setInt(1, bookAvailability);
			ps.executeUpdate();

		} catch (SQLException e) {
			// logger.error("Exception caught during setBookAvailability", e);
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}

	public Book getBookById(int value) {
		Book book = new Book();
		try {
			c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Library.Book WHERE id =?;  ");
			ps.setInt(1, value);
			ResultSet result = ps.executeQuery();
			result.next();
			book = parseBook(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);

		}
		return book;

	}

	public List<Book> getBookByName(String bookName) {

		List<Book> books = new ArrayList<Book>();
		try {

			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Library.Book WHERE name =?;  ");
			ps.setString(1, bookName);
			ResultSet result = ps.executeQuery();

			addNewBooks(result, books);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);

		}
		return books;

	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			Connection c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Library.Book;");
			ResultSet result = ps.executeQuery();
			addNewBooks(result, books);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);

		}
		return books;

	}

	private List<Book> addNewBooks(ResultSet booksResultSet, List<Book> books)
			throws SQLException {

		while (booksResultSet.next()) {
			books.add(parseBook(booksResultSet));
		}
		return books;
	}

	private Book parseBook(ResultSet booksResultSet) throws SQLException {
		Book book = new Book();
		book.setId(booksResultSet.getInt("id"));
		book.setName(booksResultSet.getString("name"));
		book.setAuthor(booksResultSet.getString("author"));
		book.setYear(booksResultSet.getInt("year"));
		book.setAvailability(booksResultSet.getInt("availability"));
		return book;
	}
	

}
