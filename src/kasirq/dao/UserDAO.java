package kasirq.dao;

import kasirq.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public User findByUsernameAndPassword(String username, String password)
            throws SQLException {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                u.setRoleId(rs.getInt("role_id"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                u.setLastUpdatedAt(rs.getTimestamp("last_updated_at").toLocalDateTime());
                return u;
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                u.setRoleId(rs.getInt("role_id"));
                list.add(u);
            }
        }
        return list;
    }

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, firstname, lastname, role_id, phone_number) VALUES (?, ?, ?, ?, ?, ?)"
      ;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setInt(5, user.getRoleId());
            ps.setString(6, user.getPhoneNumber());
            ps.executeUpdate();
        }
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET firstname = ?, lastname = ?, role_id = ?, phone_number = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setInt(3, user.getRoleId());
            ps.setString(4, user.getPhoneNumber());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        }
    }
}
