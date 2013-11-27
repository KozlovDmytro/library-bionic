package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DaoLibrary {
	private static java.sql.Connection c;

	public static Connection startConnection() throws SQLException {


		try{
		 Context ct = new InitialContext();
		 DataSource ds = (DataSource)ct.lookup("java:/comp/env/jdbc/library");
		  c = ds.getConnection();
		 System.out.print(c);
		}catch(NamingException e){
			e.printStackTrace();
		}


		return c;

	}

	public static void softStop(Connection c) {
		if (c == null) {
			return;
		}
		try {
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void softStop(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void softStop(Statement st) {
		if (st == null) {
			return;
		}
		try {
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void softStop(PreparedStatement pst) {
		if (pst == null) {
			return;
		}
		try {
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			startConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
