package ru.gb.wintermarket.carts.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.gb.wintermarket.api.dto.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Slf4j
@Data

public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void  add(ProductDto productDto) {
            for(CartItem p : items){
                if(p.getProductId().equals(productDto.getId())){
                    p.changeQuantity(1);/*setQuantity(p.getQuantity() + 1);*/
                    recalculate();
                    return;
                }
            }
        items.add(new CartItem(
                productDto.getId(),
                productDto.getTitle(),
                1,
                productDto.getPrice())
        );
        recalculate();
    }
    private void recalculate() {
        log.warn("recalculate()");
        totalPrice = new BigDecimal("0.00");
        for (CartItem item :
                items) {
            totalPrice = totalPrice.add(item.getPrice()).setScale(2, RoundingMode.HALF_UP);
        }
    }
    public void increaseProduct(ProductDto productDto) {
        for(CartItem p : items){
            if(p.getProductId().equals(productDto.getId())) {
                p.changeQuantity(1);
                recalculate();
                log.warn("after increaseProduct getQuantity = " + p.getQuantity());
                log.warn("after increaseProduct price = " + p.getPrice());
                return;
            }
        }
    }
    public void decreaseProduct(ProductDto productDto) {
        for(CartItem p : items){
            if(p.getProductId().equals(productDto.getId())) {
                if(p.getQuantity() > 1){
                    p.changeQuantity(-1);
                    recalculate();
                    log.warn("after decreaseProduct price = " + p.getPrice());
                    return;
                }
            }
        }
    }
    public void remove(ProductDto productDto) {
        if( items.removeIf(p -> p.getProductId().
                            equals(productDto.getId())) ){
            recalculate();
        }
    }
    public void clear() {
        items.clear();
        totalPrice = new BigDecimal("0.00");
    }
}

