package kasirq.dao;

import kasirq.model.TransactionDetail;
import kasirq.model.TransactionHeader;
import java.sql.*;

public class TransactionDAO {

    private final Connection conn;

    public TransactionDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertHeader(TransactionHeader h) throws SQLException {
        String sql = "INSERT INTO transaction_header (user_id, buyer_name, buyer_class_id, total_product, total_price, payment_method_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, h.getUserId());
            if (h.getBuyerName() != null) {
                ps.setString(2, h.getBuyerName());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            if (h.getBuyerClassId() != null)
                ps.setInt(3, h.getBuyerClassId());
            else
                ps.setNull(3, Types.INTEGER);

            ps.setInt(4, h.getTotalProduct());
            ps.setBigDecimal(5, h.getTotalPrice());
            ps.setInt(6, h.getPaymentMethodId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public void insertDetail(TransactionDetail d) throws SQLException {
        String sql = "INSERT INTO transaction_detail (transaction_id, product_id, qty, price, sub_total) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getTransactionId());
            ps.setInt(2, d.getProductId());
            ps.setInt(3, d.getQty());
            ps.setBigDecimal(4, d.getPrice());
            ps.setBigDecimal(5, d.getSubTotal());
            ps.executeUpdate();
        }
    }
}
