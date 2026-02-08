package kasirq.service;

import java.sql.Connection;
import java.util.List;
import kasirq.config.DatabaseConfig;
import kasirq.dao.ProductDAO;
import kasirq.model.Product;

public class ProductService {

    public List<Product> getProducts(String keyword) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new ProductDAO(conn)
                .findAll(keyword == null ? "" : keyword);
        }
    }

    public void save(Product product) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            new ProductDAO(conn).insert(product);
        }
    }

    public void update(Product product) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            new ProductDAO(conn).update(product);
        }
    }

    public void delete(int productId) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            new ProductDAO(conn).delete(productId);
        }
    }
}
