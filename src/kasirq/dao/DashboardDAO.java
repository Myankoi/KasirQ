/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirq.dao;

/**
 *
 * @author RAMADIAN
 */

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    private final Connection conn;

    public DashboardDAO(Connection conn) {
        this.conn = conn;
    }

    public BigDecimal getTotalSalesToday() throws SQLException {
        String sql = "SELECT COALESCE(SUM(total_price), 0) FROM transaction_header WHERE DATE(created_at) = CURDATE()";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
        return BigDecimal.ZERO;
    }

    public int getTotalItemsSoldToday() throws SQLException {
        String sql = "SELECT COALESCE(SUM(td.qty), 0) FROM transaction_detail td JOIN transaction_header th ON td.transaction_id = th.id WHERE DATE(th.created_at) = CURDATE()";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTransactionCountToday() throws SQLException {
        String sql = "SELECT COUNT(*) FROM transaction_header WHERE DATE(created_at) = CURDATE()";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public BigDecimal getAverageTransactionValueToday() throws SQLException {
        String sql = "SELECT COALESCE(AVG(total_price), 0) FROM transaction_header WHERE DATE(created_at) = CURDATE()";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
        return BigDecimal.ZERO;
    }

    public List<Object[]> getRecentTransactions(int limit) throws SQLException {
    List<Object[]> list = new ArrayList<>();

    String sql =
        "SELECT " +
        " th.id, " +
        " DATE(th.created_at), " +
        " TIME(th.created_at), " +
        " th.total_price, " +
        " th.total_product, " +
        " COALESCE(u.firstname, 'Unknown') " +
        "FROM transaction_header th " +
        "LEFT JOIN users u ON th.user_id = u.id " +
        "ORDER BY th.created_at DESC " +
        "LIMIT ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, limit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt(1),        // Transaction ID
                rs.getDate(2),       // Date
                rs.getTime(3),       // Time
                rs.getBigDecimal(4), // Total Amount
                rs.getInt(5),        // Items
                rs.getString(6)      // Cashier
            });
        }
    }
    return list;
}

}
