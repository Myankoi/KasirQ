/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirq.service;

import java.sql.Connection;
import java.util.List;
import kasirq.config.DatabaseConfig;
import kasirq.dao.InventoryDAO;
import kasirq.model.Product;
import kasirq.model.ProductInventory;

/**
 *
 * @author RAMADIAN
 */
public class InventoryService {
    
    public List<Product> getInventories(String keyword) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new InventoryDAO(conn)
                .findAll(keyword == null ? "" : keyword);
        }
    }


    public void update(ProductInventory productInventory) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            new InventoryDAO(conn).setStock(productInventory);
        }
    }
}
