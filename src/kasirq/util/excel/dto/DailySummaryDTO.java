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
public class DailySummaryDTO {
    private int totalTransaction;
    private int totalQty;
    private double totalRevenue;
    private int cashierCount;
    private String topProduct;
    private String topCategory;

    // Getters and Setters
    public int getTotalTransaction() { return totalTransaction; }
    public void setTotalTransaction(int totalTransaction) { this.totalTransaction = totalTransaction; }

    public int getTotalQty() { return totalQty; }
    public void setTotalQty(int totalQty) { this.totalQty = totalQty; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

    public int getCashierCount() { return cashierCount; }
    public void setCashierCount(int cashierCount) { this.cashierCount = cashierCount; }

    public String getTopProduct() { return topProduct; }
    public void setTopProduct(String topProduct) { this.topProduct = topProduct; }

    public String getTopCategory() { return topCategory; }
    public void setTopCategory(String topCategory) { this.topCategory = topCategory; }
}