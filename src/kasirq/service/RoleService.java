package kasirq.service;

import java.sql.Connection;
import java.util.List;
import kasirq.config.DatabaseConfig;
import kasirq.dao.RoleDAO;
import kasirq.model.Role;

public class RoleService {

    public List<Role> getRoles() throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            return new RoleDAO(conn).findAll();
        }
    }
}
