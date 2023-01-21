package ru.gb.wintermarket.api.dto.notUsedDtos;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long id;
    private String productTitle;
    private Long orderId;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String productTitle, Long orderId, int quantity, BigDecimal costPerProduct, BigDecimal cost) {
        this.id = id;
        this.productTitle = productTitle;
        this.orderId = orderId;
        this.quantity = quantity;
        this.pricePerProduct = costPerProduct;
        this.price = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCostPerProduct() {
        return pricePerProduct;
    }

    public void setCostPerProduct(BigDecimal costPerProduct) {
        this.pricePerProduct = costPerProduct;
    }

    public BigDecimal getCost() {
        return price;
    }

    public void setCost(BigDecimal cost) {
        this.price = cost;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "id=" + id +
                ", productTitle='" + productTitle + '\'' +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", costPerProduct=" + pricePerProduct +
                ", cost=" + price +
                '}';
    }
}
