package kasirq.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kasirq.model.Product;
import kasirq.model.ProductInventory;

public class InventoryDAO {

    private final Connection conn;

    public InventoryDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List<Product> findAll(String keyword) throws SQLException {

        List<Product> list = new ArrayList<>();

        String sql =
            "SELECT p.id, p.category_id, c.name AS category_name, " +
            "p.name, p.price, i.stock, p.image_path " +
            "FROM product p " +
            "JOIN product_inventory i ON p.id = i.product_id " +
            "JOIN category c ON p.category_id = c.id " +
            "WHERE p.name LIKE ? " +
            "ORDER BY p.name";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setCategoryName(rs.getString("category_name"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setStock(rs.getInt("stock"));
            p.setImagePath(rs.getString("image_path"));
            list.add(p);
        }
        return list;
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
    
    public void setStock(ProductInventory productInventory) throws SQLException {
        String sql = "UPDATE product_inventory SET stock = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productInventory.getStock());
            ps.setInt(2, productInventory.getProductId());
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
