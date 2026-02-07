package kasirq.service;

import kasirq.config.DatabaseConfig;
import kasirq.dao.DashboardDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardService {

    public Map<String, Object> getDashboardOverview() throws Exception {

        try (Connection conn = DatabaseConfig.getConnection()) {

            DashboardDAO dao = new DashboardDAO(conn);

            Map<String, Object> data = new HashMap<>();

            data.put("totalSales", dao.getTotalSalesToday());
            data.put("totalItems", dao.getTotalItemsSoldToday());
            data.put("transactionCount", dao.getTransactionCountToday());
            data.put("averageTransaction", dao.getAverageTransactionValueToday());
            data.put("recentTransactions", dao.getRecentTransactions(10));

            return data;
        }
    }
}
