//package kasirq.dao;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReportDAO {
//
//    private final Connection conn;
//
//    public ReportDAO(Connection conn) {
//        this.conn = conn;
//    }
//
//    /**
//     * Get daily transaction header summary
//     */
//    public List<Object[]> getDailyTransactionSummary(Date date)
//            throws SQLException {
//
//        List<Object[]> list = new ArrayList<>();
//
//        String sql = """
//            SELECT 
//                th.id,
//                th.created_at,
//                u.username,
//                pm.name AS payment_method,
//                th.buyer_name,
//                th.total_product,
//                th.total_price
//            FROM transaction_header th
//            JOIN users u ON th.user_id = u.id
//            JOIN payment_method pm ON th.payment_method_id = pm.id
//            WHERE DATE(th.created_at) = ?
//            ORDER BY th.created_at ASC
//        """;
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setDate(1, date);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Object[] row = new Object[]{
//                        rs.getInt("id"),
//                        rs.getTimestamp("created_at"),
//                        rs.getString("username"),
//                        rs.getString("payment_method"),
//                        rs.getString("buyer_name"),
//                        rs.getInt("total_product"),
//                        rs.getBigDecimal("total_price")
//                };
//                list.add(row);
//            }
//        }
//        return list;
//    }
//
//    /**
//     * Get daily transaction detail rows
//     */
//    public List<Object[]> getDailyTransactionDetails(Date date)
//            throws SQLException {
//
//        List<Object[]> list = new ArrayList<>();
//
//        String sql = """
//            SELECT
//                td.transaction_id,
//                p.name AS product_name,
//                td.qty,
//                td.price,
//                td.sub_total
//            FROM transaction_detail td
//            JOIN transaction_header th ON td.transaction_id = th.id
//            JOIN product p ON td.product_id = p.id
//            WHERE DATE(th.created_at) = ?
//            ORDER BY td.transaction_id ASC
//        """;
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setDate(1, date);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Object[] row = new Object[]{
//                        rs.getInt("transaction_id"),
//                        rs.getString("product_name"),
//                        rs.getInt("qty"),
//                        rs.getBigDecimal("price"),
//                        rs.getBigDecimal("sub_total")
//                };
//                list.add(row);
//            }
//        }
//        return list;
//    }
//}
