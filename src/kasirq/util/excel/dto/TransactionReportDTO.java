/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirq.util.excel.dto;

/**
 *
 * @author RAMADIAN
 */
import java.time.LocalDateTime;

public class TransactionReportDTO {
    private int transactionId;
    private String cashier;
    private String buyerName;
    private String buyerClass;
    private int totalProduct;
    private double totalPrice;
    private String paymentMethod;
    private LocalDateTime createdAt;

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public String getCashier() { return cashier; }
    public void setCashier(String cashier) { this.cashier = cashier; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public String getBuyerClass() { return buyerClass; }
    public void setBuyerClass(String buyerClass) { this.buyerClass = buyerClass; }

    public int getTotalProduct() { return totalProduct; }
    public void setTotalProduct(int totalProduct) { this.totalProduct = totalProduct; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}