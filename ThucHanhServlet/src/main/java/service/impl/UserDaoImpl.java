package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import service.UserDao;
import model.User;
// Bước 1: Import lớp DBConnection vừa tạo
import config.DBConnection;

public class UserDaoImpl implements UserDao {

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM [User] WHERE userName = ?";
        
        // Bước 2: Sử dụng DBConnection.getConnection()
        try (Connection conn = DBConnection.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid, phone, createddate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Bước 2: Sử dụng DBConnection.getConnection()
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreatedDate());
            
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String sql = "SELECT 1 FROM [User] WHERE email = ?";
        
        // Bước 2: Sử dụng DBConnection.getConnection()
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, email);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String sql = "SELECT 1 FROM [User] WHERE username = ?";
        
        // Bước 2: Sử dụng DBConnection.getConnection()
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String sql = "SELECT 1 FROM [User] WHERE phone = ?";
        
        // Bước 2: Sử dụng DBConnection.getConnection()
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, phone);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}