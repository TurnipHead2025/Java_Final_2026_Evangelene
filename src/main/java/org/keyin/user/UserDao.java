package org.keyin.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.keyin.database.DatabaseConnection;

public class UserDao {

    //Finds a user in the DB by thier username. Creates and returns a new user obj if found, null if not found
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user =  new User(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("role")
                    );
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }
        }

        return null;
    }  
    
    // Inserts a user into the DB. The password should already be hashed before calling this method. 
    public void registerUser(User user) throws SQLException{
        String sql = "INSERT INTO users (username, password, email, address, phone, role) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());  
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getPhoneNumber());  
            pstmt.setString(6, user.getRole());
            pstmt.executeUpdate();                
            }
    }

    //Creates and returns a list of all users in the Db (Admin only)
    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    User user = new User(
                           rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("role")
                    );
                    user.setId(rs.getInt("id"));
                    users.add(user);
                }
            }
        return users;    
    }

    //Deletes a user from the DB by their ID (Admin only)
    public void deleteUser(int id) throws SQLException{
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();        
            }
    }
}
