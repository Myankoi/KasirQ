package kasirq.service;

import java.sql.Connection;
import java.util.List;
import kasirq.config.DatabaseConfig;
import kasirq.dao.UserDAO;
import kasirq.model.User;

public class UserService {

    public User login(String username, String password) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new UserDAO(conn)
                .findByUsernameAndPassword(username, password);
        }
    }
    
    public void update(User user, boolean updatePassword) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            UserDAO dao = new UserDAO(conn);
            if (updatePassword) {
                dao.updateWithPassword(user);
            } else {
                dao.update(user);
            }
        }
    }

    public List<User> getUsers() throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new UserDAO(conn).findAll();
        }
    }

    public void add(User user) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            new UserDAO(conn).insert(user);
        }
    }

    public void update(User user) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            new UserDAO(conn).update(user);
        }
    }
}
