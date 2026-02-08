package kasirq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kasirq.model.ClassRoom;

public class ClassDAO {

    private final Connection conn;

    public ClassDAO(Connection conn) {
        this.conn = conn;
    }

    public List<ClassRoom> findAll() throws Exception {
        List<ClassRoom> list = new ArrayList<>();

        String sql = "SELECT id, name FROM class ORDER BY name";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClassRoom cls = new ClassRoom();
                cls.setId(rs.getInt("id"));
                cls.setName(rs.getString("name"));
                list.add(cls);
            }
        }
        return list;
    }
}
