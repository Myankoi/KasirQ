/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirq.service;

import java.sql.Connection;
import java.util.List;
import kasirq.config.DatabaseConfig;
import kasirq.dao.ProductDAO;
import kasirq.model.Product;

/**
 *
 * @author RAMADIAN
 */
public class ProductService {

    public List<Product> getProducts(String keyword) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            ProductDAO dao = new ProductDAO(conn);
            return dao.search(keyword == null ? "" : keyword);
        }
    }
}
