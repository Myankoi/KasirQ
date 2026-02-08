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

    public void insert(Product product) throws SQLException {

        String sqlProduct =
            "INSERT INTO product (category_id, name, price, image_path) " +
            "VALUES (?, ?, ?, ?)";

        String sqlInventory =
            "INSERT INTO product_inventory (product_id, stock) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);

            // insert product
            PreparedStatement ps =
                conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getImagePath());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("Failed to get product id");
            }

            int productId = rs.getInt(1);

            // insert inventory
            PreparedStatement psInv = conn.prepareStatement(sqlInventory);
            psInv.setInt(1, productId);
            psInv.setInt(2, product.getStock());
            psInv.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void update(Product product) throws SQLException {

        String sqlProduct =
            "UPDATE product SET category_id=?, name=?, price=?, image_path=? " +
            "WHERE id=?";

        String sqlInventory =
            "UPDATE product_inventory SET stock=? WHERE product_id=?";

        try {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(sqlProduct);
            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getImagePath());
            ps.setInt(5, product.getId());
            ps.executeUpdate();

            PreparedStatement psInv = conn.prepareStatement(sqlInventory);
            psInv.setInt(1, product.getStock());
            psInv.setInt(2, product.getId());
            psInv.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void delete(int productId) throws SQLException {

        String sqlInventory =
            "DELETE FROM product_inventory WHERE product_id=?";

        String sqlProduct =
            "DELETE FROM product WHERE id=?";

        try {
            conn.setAutoCommit(false);

            PreparedStatement ps1 = conn.prepareStatement(sqlInventory);
            ps1.setInt(1, productId);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(sqlProduct);
            ps2.setInt(1, productId);
            ps2.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
