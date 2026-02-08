package kasirq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kasirq.model.PaymentMethod;

public class PaymentMethodDAO {

    private final Connection conn;
    private PaymentMethod paymentMethod;

    public PaymentMethodDAO(Connection conn) {
        this.conn = conn;
    }

    public List<PaymentMethod> findAll() throws Exception {
        List<PaymentMethod> list = new ArrayList<>();

        String sql = "SELECT id, name FROM payment_method ORDER BY name";

        try (PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                paymentMethod = new PaymentMethod();
                paymentMethod.setId(rs.getInt("id"));
                paymentMethod.setName(rs.getString("name"));
                list.add(paymentMethod);
            }
        }
        return list;
    }
}
