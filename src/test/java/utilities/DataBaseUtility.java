package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseUtility {

	private static final String URL = "";
	private static final String USER = "";
	private static final String PASSWORD = "";

	public static Connection getConnection() {

		Connection connect = null;
		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connect;
	}

	
	// Method to add a User
	public static void addUser(String Username, String Password) throws SQLException {
		String query = "INSERT INTO users (username, password) VALUES (?, ?)";

		Connection connect = getConnection();

		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setString(1, query);

		stmt.executeUpdate();
	}
	

	// Method to query user by Username
	public static String getUserPassword(String Username) {
		String password = " ";
		String query = "";
		try {
			Connection connect = getConnection();
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, Username);
			ResultSet rset = stmt.executeQuery();

			if (rset.next()) {
				password = rset.getString("password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return password;
	}
}
