package kasirq.dao;

import kasirq.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List<Product> search(String keyword) throws SQLException {

    List<Product> list = new ArrayList<>();

    String sql =
        "SELECT p.id, p.name, p.price, i.stock " +
        "FROM product p " +
        "JOIN product_inventory i ON p.id = i.product_id " +
        "WHERE p.name LIKE ? " +
        "ORDER BY p.name";

    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, "%" + keyword + "%");

    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setStock(rs.getInt("stock"));
        list.add(p);
    }
    return list;
}


    public int insert(Product product) throws SQLException {
        String sql = "INSERT INTO product (category_id, name, price, image_path) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
            conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getImagePath());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE product SET category_id = ?, name = ?, price = ?, image_path = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getImagePath());
            ps.setInt(5, product.getId());
            ps.executeUpdate();
        }
    }

    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setImagePath(rs.getString("image_path"));
                list.add(p);
            }
        }
        return list;
    }
}
