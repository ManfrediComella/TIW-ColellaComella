package online_auction.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import online_auction.beans.UserBean;

public class UserDAO {
	private Connection con;
	
	public UserDAO(Connection connection) {
		this.con = connection;
	}

	public int registerUser(String username, String password) throws SQLException {
		int code = 0;
		String query = "INSERT into user_table (username, password)   VALUES(?, ?)";

		PreparedStatement pstatement = null;
		try {
			pstatement = con.prepareStatement(query);

			pstatement.setString(1, username);
			pstatement.setString(2, password);

			code = pstatement.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			try {
				pstatement.close();
			} catch (Exception e1) {
				throw e1;
			}
		}
		return code;
	}
	
	

	public int deleteUser(UserBean user) throws SQLException {
		String query = "DELETE FROM user_table WHERE username = ?";
		int resultCode = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setString(1, user.getUsername());
			resultCode = pstatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			try {
				if (pstatement != null)
					pstatement.close();
			} catch (Exception e1) {
				throw e1;
			}
		}
		return resultCode;
	}

	public UserBean checkCredentials(String username, String Password) throws SQLException {
		return null;
	}

}
