package kasirq.service;

import kasirq.config.DatabaseConfig;
import kasirq.dao.UserDAO;
import kasirq.model.User;

import java.sql.Connection;

public class AuthService {
    
    public User login(String username, String password) throws Exception {
        
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        try (Connection conn = DatabaseConfig.getConnection()) {

            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.findByUsernameAndPassword(username, password);

            if (user == null) {
                return null;
            }

            return user;
        }
    }
}
