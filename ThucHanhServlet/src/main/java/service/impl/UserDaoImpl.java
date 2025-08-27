package service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import service.*;
import model.User;

public class UserDaoImpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
    @Override
    public User get(String username) {
    	String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=LapTrinhWeb;user=sa;password=nhacc123@;encrypt=true;trustServerCertificate=true;";
        String sql = "SELECT * FROM [User] WHERE userName = ?";
        try {
            // Nạp driver ở đây, trước khi thực hiện kết nối
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 

            try (Connection conn = DriverManager.getConnection(connectionUrl);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));

                        user.setEmail(rs.getString("email"));

                        user.setUserName(rs.getString("username"));

                        user.setFullName(rs.getString("fullname"));

                        user.setPassWord(rs.getString("password"));

                        user.setAvatar(rs.getString("avatar"));

                        user.setRoleid(rs.getInt("roleid"));

                        user.setPhone(rs.getString("phone"));

                        user.setCreatedDate(rs.getDate("createdDate"));

                        return user;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
