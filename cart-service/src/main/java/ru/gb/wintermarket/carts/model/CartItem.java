package ru.gb.wintermarket.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    public String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public CartItem(Long productId, String productTitle, int quantity, BigDecimal pricePerProduct) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;

        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
    public void changeQuantity(int delta) {
        quantity += delta;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
        log.warn("delta= " + delta +"quantity= "+quantity + "price = " + price);
    }


}

