package robert.reversi_v5web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import robert.reversi_v5web.api.DBUserDAO;
import robert.reversi_v5web.api.UserDAO;
import robert.reversi_v5web.domain.CurrentJavaSqlTimestamp;

@Component
public class DBUserDAOImpl /* implements DBUserDAO */ {
	@Autowired
	private DataSource dataSource;
	// private static DataSource dataSource;

	// String sql = "INSERT INTO user " + "(name) VALUES (?)";
	String sql = "INSERT INTO USER " + "(NAME, email, pass, age, last_log) VALUES (?, ?, ?, ?, ?)";
	private Connection conn = null;

//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
	public boolean addUser(UserDAO userDao) {
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userDao.getName());
			ps.setString(2, userDao.getEmail());
			ps.setString(3, userDao.getPass());
			ps.setInt(4, userDao.getAge());
			CurrentJavaSqlTimestamp ts = new CurrentJavaSqlTimestamp();
			Timestamp timestamp = ts.getCurrentJavaSqlTimestamp();
			ps.setTimestamp(5, timestamp);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
					return true;
				} catch (SQLException e) {
				}
			}
		}
		return false;
	}
}
