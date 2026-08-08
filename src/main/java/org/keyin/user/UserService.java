package org.keyin.user;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDao userDao;

    //constructor
    public UserService() {
    this.userDao = new UserDao();
    }

        //// Takes a User obj, hashes the password, sets the hashed password on the obj, then calls registerUser method from UserDao which inserts items into the DB
    public void registerUser(User user) throws SQLException{
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDao.registerUser(user);
    }

    // finds user by username from DB, then uses BCrypt to compare the entered password with the hashed one stored in DB. Returns the User obj if login successful, null if not
    public User loginForUser(String username, String password) throws SQLException{
        User user = userDao.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())){
            return user;
        }
        return null;
    }

    //Returns a list of all users- calls the UserDao getAllUsers method (Admin only)
    public List<User> getAllUsers() throws SQLException{
        return userDao.getAllUsers();
    }

    //Deletes a user by ID- calls UserDao deleteUser method (Admin Only)
    public void deleteUser(int id) throws SQLException{
        userDao.deleteUser(id);
    }
}
