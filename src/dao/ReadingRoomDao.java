package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.ReadingRoom;
import entity.User;

public class ReadingRoomDao {
	private PreparedStatement ps;
	private Statement st;
	private Connection c;
	private BookDao bookDao = new BookDao();
	private UserDao userDao = new UserDao();

	public void insertInReadingRoom(int readingBookId, int readingUserId) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("INSERT INTO Library.Reading_room"
					+ "(id_user, id_book) VALUES (?, ?);");
			ps.setInt(1, readingUserId);
			ps.setInt(2, readingBookId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}

	public List<ReadingRoom> getAllInReadingRoom() {
		List<ReadingRoom> readingRoom = new ArrayList<ReadingRoom>();

		ResultSet result;
		try {
			c = DaoLibrary.startConnection();
			st = c.createStatement();
			result = st.executeQuery("SELECT * FROM Library.Reading_room ");

			while (result.next()) {
				ReadingRoom currRoom = new ReadingRoom();
				currRoom.setId(result.getInt("id"));
				User user = userDao.getUserById(result.getInt("id_user"));
				currRoom.setUser(user);
				Book book = bookDao.getBookById(result.getInt("id_book"));
				currRoom.setBook(book);
				readingRoom.add(currRoom);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(st);
			DaoLibrary.softStop(c);
		}
		return readingRoom;

	}

	public void deleteBookFromReadingRoom(Book book) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("DELETE FROM Library.reading_room WHERE id_book=?;");
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
			ps = c.prepareStatement("SELECT * FROM Library.reading_room WHERE id_book=?;");
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