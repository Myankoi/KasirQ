/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirq.util.excel;

/**
 *
 * @author RAMADIAN
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import kasirq.util.excel.dto.*;

public class ReportDAO {

    private final Connection conn;

    public ReportDAO(Connection conn) {
        this.conn = conn;
    }
    
    public DailySummaryDTO getDailySummary(LocalDate date) throws SQLException {
        String sql = "SELECT "
                + "  COUNT(DISTINCT th.id) total_tx, "
                + "  SUM(td.qty) total_qty, "
                + "  SUM(td.sub_total) revenue, "
                + "  COUNT(DISTINCT th.user_id) cashier_count "
                + "FROM transaction_header th "
                + "JOIN transaction_detail td ON th.id = td.transaction_id "
                + "WHERE DATE(th.created_at) = ?";

        DailySummaryDTO dto = new DailySummaryDTO();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dto.setTotalTransaction(rs.getInt("total_tx"));
                dto.setTotalQty(rs.getInt("total_qty"));
                dto.setTotalRevenue(rs.getDouble("revenue"));
                dto.setCashierCount(rs.getInt("cashier_count"));
            }
        }

        dto.setTopProduct(getTopProduct(date));
        dto.setTopCategory(getTopCategory(date));

        return dto;
    }

    private String getTopProduct(LocalDate date) throws SQLException {
        String sql = "SELECT p.name, SUM(td.qty) qty "
                + "FROM transaction_detail td "
                + "JOIN transaction_header th ON td.transaction_id = th.id "
                + "JOIN product p ON td.product_id = p.id "
                + "WHERE DATE(th.created_at) = ? "
                + "GROUP BY p.id "
                + "ORDER BY qty DESC "
                + "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString("name") : "-";
        }
    }

    private String getTopCategory(LocalDate date) throws SQLException {
        String sql = "SELECT c.name, SUM(td.qty) qty "
                + "FROM transaction_detail td "
                + "JOIN transaction_header th ON td.transaction_id = th.id "
                + "JOIN product p ON td.product_id = p.id "
                + "JOIN category c ON p.category_id = c.id "
                + "WHERE DATE(th.created_at) = ? "
                + "GROUP BY c.id "
                + "ORDER BY qty DESC "
                + "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString("name") : "-";
        }
    }

    // ================= TRANSACTIONS =================
    public List<TransactionReportDTO> getTransactions(LocalDate date) throws SQLException {
        String sql = "SELECT th.id, th.created_at, "
                + "       CONCAT(u.firstname,' ',u.lastname) cashier, "
                + "       th.buyer_name, cl.name class, "
                + "       th.total_product, th.total_price, "
                + "       pm.name payment "
                + "FROM transaction_header th "
                + "JOIN users u ON th.user_id = u.id "
                + "LEFT JOIN class cl ON th.buyer_class_id = cl.id "
                + "JOIN payment_method pm ON th.payment_method_id = pm.id "
                + "WHERE DATE(th.created_at) = ? "
                + "ORDER BY th.created_at";

        List<TransactionReportDTO> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TransactionReportDTO dto = new TransactionReportDTO();
                dto.setTransactionId(rs.getInt("id"));
                dto.setCashier(rs.getString("cashier"));
                dto.setBuyerName(rs.getString("buyer_name"));
                dto.setBuyerClass(rs.getString("class"));
                dto.setTotalProduct(rs.getInt("total_product"));
                dto.setTotalPrice(rs.getDouble("total_price"));
                dto.setPaymentMethod(rs.getString("payment"));
                dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                list.add(dto);
            }
        }
        return list;
    }

    // ================= DETAIL =================
    public List<TransactionDetailReportDTO> getTransactionDetails(LocalDate date) throws SQLException {
        String sql = "SELECT th.id tx_id, p.name product, c.name category, "
                + "       td.qty, td.price, td.sub_total "
                + "FROM transaction_detail td "
                + "JOIN transaction_header th ON td.transaction_id = th.id "
                + "JOIN product p ON td.product_id = p.id "
                + "JOIN category c ON p.category_id = c.id "
                + "WHERE DATE(th.created_at) = ?";

        List<TransactionDetailReportDTO> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TransactionDetailReportDTO dto = new TransactionDetailReportDTO();
                dto.setTransactionId(rs.getInt("tx_id"));
                dto.setProductName(rs.getString("product"));
                dto.setCategoryName(rs.getString("category"));
                dto.setQty(rs.getInt("qty"));
                dto.setPrice(rs.getDouble("price"));
                dto.setSubTotal(rs.getDouble("sub_total"));
                list.add(dto);
            }
        }
        return list;
    }

    // ================= PRODUCT SUMMARY =================
    public List<ProductSummaryDTO> getProductSummary(LocalDate date) throws SQLException {
        String sql = "SELECT p.name product, c.name category, "
                + "       SUM(td.qty) total_sold, "
                + "       SUM(td.sub_total) revenue "
                + "FROM transaction_detail td "
                + "JOIN transaction_header th ON td.transaction_id = th.id "
                + "JOIN product p ON td.product_id = p.id "
                + "JOIN category c ON p.category_id = c.id "
                + "WHERE DATE(th.created_at) = ? "
                + "GROUP BY p.id "
                + "ORDER BY total_sold DESC";

        List<ProductSummaryDTO> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductSummaryDTO dto = new ProductSummaryDTO();
                dto.setProductName(rs.getString("product"));
                dto.setCategoryName(rs.getString("category"));
                dto.setTotalSold(rs.getInt("total_sold"));
                dto.setRevenue(rs.getDouble("revenue"));
                list.add(dto);
            }
        }
        return list;
    }
}