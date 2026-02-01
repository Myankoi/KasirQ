package kasirq.service;

import kasirq.config.DatabaseConfig;
import kasirq.dao.InventoryDAO;
import kasirq.dao.TransactionDAO;
import kasirq.model.TransactionDetail;
import kasirq.model.TransactionHeader;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import kasirq.model.CartItem;
import kasirq.model.TransactionRequest;

public class TransactionService {

    public void saveTransaction(TransactionRequest req) throws Exception {
        if (req == null || req.getItems() == null || req.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Connection conn = null;

        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);

            InventoryDAO inventoryDAO = new InventoryDAO(conn);
            TransactionDAO transactionDAO = new TransactionDAO(conn);
            
            for (CartItem item : req.getItems()) {
                int stock = inventoryDAO.getStock(item.getProductId());
                if (stock < item.getQty()) {
                    throw new Exception("Stock not enough for product ID: " + item.getProductId());
                }
            }
            
            int totalProduct = 0;
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (CartItem item : req.getItems()) {
                totalProduct += item.getQty();
                totalPrice = totalPrice.add(
                        item.getPrice().multiply(BigDecimal.valueOf(item.getQty()))
                );
            }

           
            TransactionHeader header = new TransactionHeader();
            header.setUserId(req.getUserId());
            header.setBuyerName(req.getBuyerName());
            header.setBuyerClassId(req.getBuyerClassId());
            header.setTotalProduct(totalProduct);
            header.setTotalPrice(totalPrice);
            header.setPaymentMethodId(req.getPaymentMethodId());

            int transactionId = transactionDAO.insertHeader(header);

            if (transactionId <= 0) {
                throw new SQLException("Failed to create transaction header");
            }

           
            for (CartItem item : req.getItems()) {

                BigDecimal subTotal =
                        item.getPrice().multiply(BigDecimal.valueOf(item.getQty()));

                TransactionDetail detail = new TransactionDetail();
                detail.setTransactionId(transactionId);
                detail.setProductId(item.getProductId());
                detail.setQty(item.getQty());
                detail.setPrice(item.getPrice());      
                detail.setSubTotal(subTotal);

                transactionDAO.insertDetail(detail);
                inventoryDAO.reduceStock(item.getProductId(), item.getQty());
            }

            conn.commit();

        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { }
            }
            throw e;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { }
            }
        }
    }
}
