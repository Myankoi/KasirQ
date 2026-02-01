package kasirq.model;

import java.util.List;

public class TransactionRequest {
    private int userId;
    private Integer buyerClassId; // nullable
    private String buyerName;      // optional
    private int paymentMethodId;
    private List<CartItem> items;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Integer getBuyerClassId() { return buyerClassId; }
    public void setBuyerClassId(Integer buyerClassId) { this.buyerClassId = buyerClassId; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public int getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(int paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
