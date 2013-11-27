package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Book;
import entity.Order;
import entity.User;

public class OrderDao {

	private PreparedStatement ps;
	private Statement st;
	private Connection c;

	private BookDao bookDao = new BookDao();
	private UserDao userDao = new UserDao();

	public Order getOrderrById(String orderId) {
		Order currOrder = new Order();
		ResultSet result;
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Library.Order WHERE id=?; ");
			ps.setString(1, orderId);
			result = ps.executeQuery();

			while (result.next()) {
				currOrder.setId(result.getInt("id"));
				User user = userDao.getUserById(result.getInt("id_user"));
				currOrder.setUser(user);
				Book book = bookDao.getBookById(result.getInt("id_book"));
				currOrder.setBook(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
		return currOrder;

	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();

		ResultSet result;
		try {
			c = DaoLibrary.startConnection();
			st = c.createStatement();
			result = st.executeQuery("SELECT * FROM Library.Order ");

			while (result.next()) {
				Order currOrder = new Order();
				currOrder.setId(result.getInt("id"));
				User user = userDao.getUserById(result.getInt("id_user"));
				currOrder.setUser(user);
				Book book = bookDao.getBookById(result.getInt("id_book"));
				currOrder.setBook(book);
				orders.add(currOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(st);
			DaoLibrary.softStop(c);
		}
		return orders;

	}
	
	public List<Order> getOrdersByUserName(String name) {
		List<Order> orders = new ArrayList<Order>();
		UserDao userDao = new UserDao();
		User currUser = userDao.getUserByLogin(name) ;
		ResultSet result;
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Library.Order WHERE id_user=?; ");
			ps.setInt(1, currUser.getId());
			result = ps.executeQuery();

			while (result.next()) {
				Order currOrder = new Order();
				currOrder.setId(result.getInt("id"));
				User user = userDao.getUserById(result.getInt("id_user"));
				currOrder.setUser(user);
				Book book = bookDao.getBookById(result.getInt("id_book"));
				currOrder.setBook(book);
				orders.add(currOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(st);
			DaoLibrary.softStop(c);
		}
		return orders;

	}

	public void insertInOrder(int orderBookId, int orderUserId) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("INSERT INTO Library.Order(id_user, id_book) "
					+ "VALUES (?, ?);");
			ps.setInt(1, orderUserId);
			ps.setInt(2, orderBookId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}

	public void deleteFromOrderById(int orderId) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("DELETE FROM Library.Order WHERE id=?;");
			ps.setInt(1, orderId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}

}
