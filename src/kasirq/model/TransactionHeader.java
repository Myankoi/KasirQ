package kasirq.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionHeader {

    private int id;
    private int userId;
    private String buyerName;
    private Integer buyerClassId;
    private int totalProduct;
    private BigDecimal totalPrice;
    private int paymentMethodId;
    private LocalDateTime createdAt;

    public TransactionHeader() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getBuyerClassId() {
        return buyerClassId;
    }

    public void setBuyerClassId(Integer buyerClassId) {
        this.buyerClassId = buyerClassId;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
