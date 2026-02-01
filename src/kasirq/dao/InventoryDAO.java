package kasirq.dao;

import java.sql.*;

public class InventoryDAO {

    private final Connection conn;

    public InventoryDAO(Connection conn) {
        this.conn = conn;
    }

    public int getStock(int productId) throws SQLException {
        String sql = "SELECT stock FROM product_inventory WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            }
        }
        return 0;
    }

    public void insert(int productId, int stock) throws SQLException {
        String sql = "INSERT INTO product_inventory (product_id, stock) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setInt(2, stock);
            ps.executeUpdate();
        }
    }

    public void reduceStock(int productId, int qty) throws SQLException {
        String sql = "UPDATE product_inventory SET stock = stock - ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }
}
