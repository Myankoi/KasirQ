package kasirq.dao;

import kasirq.model.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    private final Connection conn;

    public RoleDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Role> findAll() throws SQLException {
        List<Role> list = new ArrayList<>();

        String sql = "SELECT id, name, level FROM role ORDER BY id";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setLevel(rs.getString("level"));
                list.add(r);
            }
        }
        return list;
    }
}
