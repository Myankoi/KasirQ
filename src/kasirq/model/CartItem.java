package kasirq.model;

import java.math.BigDecimal;

public class CartItem {
    private int productId;
    private String productName;
    private int qty;
    private BigDecimal price;

    public CartItem() {}
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(qty));
    }
}
