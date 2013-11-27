package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Card;
import entity.User;

public class CardDao {
	private PreparedStatement ps;
	private Connection c;
	private Statement st;
	private BookDao bookDao = new BookDao();
	private UserDao userDao = new UserDao();

	public void insertOnCard(int cardBookId, int cardUserId) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("INSERT INTO Library.Card"
					+ "(id_user, id_book) VALUES (?, ?);");
			ps.setInt(1, cardUserId);
			ps.setInt(2, cardBookId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}

	public List<Card> getAllOnCards() {
		List<Card> cards = new ArrayList<Card>();

		ResultSet result;
		try {
			c = DaoLibrary.startConnection();
			st = c.createStatement();
			result = st.executeQuery("SELECT * FROM Library.Card ");

			while (result.next()) {
				Card currCard = new Card();
				currCard.setId(result.getInt("id"));
				User user = userDao.getUserById(result.getInt("id_user"));
				currCard.setUser(user);
				Book book = bookDao.getBookById(result.getInt("id_book"));
				currCard.setBook(book);
				cards.add(currCard);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(st);
			DaoLibrary.softStop(c);
		}
		return cards;

	}

	public void deleteBookFromCard(Book book) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("DELETE FROM Library.card WHERE id_book=?;");
			ps.setInt(1, book.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}

	public boolean isBookPresent(Book book) {
		boolean bookPresent = false;
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Library.card WHERE id_book=?;");
			ps.setInt(1, book.getId());
			ResultSet result = ps.executeQuery();
			bookPresent = result.first();
			
			System.out.println(bookPresent);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
		return bookPresent;
	}

	
}
