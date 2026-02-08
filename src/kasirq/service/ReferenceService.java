package kasirq.service;

import kasirq.config.DatabaseConfig;
import kasirq.dao.ClassDAO;
import kasirq.dao.PaymentMethodDAO;
import kasirq.model.ClassRoom;
import kasirq.model.PaymentMethod;

import java.sql.Connection;
import java.util.List;
import kasirq.dao.CategoryDAO;
import kasirq.model.Category;

public class ReferenceService {

    public List<PaymentMethod> getPaymentMethods() throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new PaymentMethodDAO(conn).findAll();
        }
    }

    public List<ClassRoom> getClasses() throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new ClassDAO(conn).findAll();
        }
    }
    
    public List<Category> getCategories() throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new CategoryDAO(conn).findAll();
        }
    }
}
