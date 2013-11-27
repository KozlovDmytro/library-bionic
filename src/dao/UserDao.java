package dao;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private PreparedStatement ps;
	private Connection c;
	private ResultSet result;

	public User getUserByLogin(String login) {
		User currUser = new User();

		try {
			c = DaoLibrary.startConnection();
			System.out.println(c);
			ps = c.prepareStatement("SELECT * FROM Library.User WHERE login=?; ");

			ps.setString(1, login);
			result = ps.executeQuery();
			result.next();
			currUser = parseUser(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {

			DaoLibrary.softStop(result);
			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
		return currUser;

	}

	public User getUserById(int id) {
		User currUser = new User();

		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Library.User WHERE id=?; ");

			ps.setInt(1, id);
			result = ps.executeQuery();
			result.next();
			currUser = parseUser(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {

			DaoLibrary.softStop(result);
			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
		return currUser;
	}

	private User parseUser(ResultSet booksResultSet) throws SQLException {
		User user = new User();
		user.setId(booksResultSet.getInt("id"));
		user.setLogin(booksResultSet.getString("login"));
		user.setPass(booksResultSet.getString("pass"));
		user.setAccess(booksResultSet.getInt("access"));
		return user;
	}

}
