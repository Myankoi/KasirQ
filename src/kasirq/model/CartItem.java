package kasirq.model;

import java.math.BigDecimal;

public class CartItem {
    private int productId;
    private int qty;
    private BigDecimal price; // price at transaction time

    public CartItem() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
