package hashfeed;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.ObservableList;

public class DBUtil {

	private static String dataBase = "jdbc:mysql://localhost/";
	private static String userName;
	private static String password;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	private static Connection conn = null;
	private static boolean connected = false;
	private String date = LocalDate.now().toString();

	public static Connection getConnection(String db, String user,
			String userPassword) {
		try {

			dataBase = dataBase + db;
			userName = user;
			password = userPassword;

			// connect to database
			conn = DriverManager.getConnection(dataBase, userName, password);
			connected = true;
			System.out.println("connected");

		} catch (SQLException s) {
			s.printStackTrace();
		}
		return conn;
	}

	public static boolean writeToDataBase(String replyToId, String date,
			String reminder) throws SQLException {
		// getConnection(dataBase, userName, password);

		if (connected) {
			PreparedStatement pstmt = null;

			try {
				String sql = "INSERT INTO reminders (reminderId,"
						+ "replyToId, date, reminder) VALUES(NULL,?, ?, ?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, replyToId);
				pstmt.setString(2, date);
				pstmt.setString(3, reminder);

				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null)
					pstmt.close();
			}

		} else {
			System.out.print("Not connected");
			return false;
		}
		return true;

	}

	public static boolean readFromDatabase(String reminder) throws SQLException {

		if (connected) {

			Statement statement = null; // query statement
			ResultSet resultSet = null; // manages results

			try {
				// create Statement for querying database
				statement = conn.createStatement();

				// query database
				resultSet = statement
						.executeQuery("SELECT reminder FROM reminders");

				while (resultSet.next()) {
					String dbResultSet = resultSet.getString(1).trim();

					if (reminder.equalsIgnoreCase(dbResultSet)) {
						return true;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (statement != null && resultSet != null) {
					statement.close();
					resultSet.close();
				}

			}

		}
		return false;

	}

	public void disconnectFromDB() {
		if (connected) {
			// close statement and connection
			try {
				resultSet.close();
				statement.close();
				conn.close();
			}// end try
			catch (SQLException sE) {
				sE.printStackTrace();
			}// end catch
			finally // update DB connection status
			{
				connected = false;
			}
		}
	}
}