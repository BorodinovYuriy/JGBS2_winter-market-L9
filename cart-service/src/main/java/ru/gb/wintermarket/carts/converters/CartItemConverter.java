package ru.gb.wintermarket.carts.converters;

import org.springframework.stereotype.Component;
import ru.gb.wintermarket.api.dto.CartItemDto;
import ru.gb.wintermarket.carts.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto dto = new CartItemDto();
        dto.setProductId(cartItem.getProductId());
        dto.setProductTitle(cartItem.getProductTitle());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPricePerProduct(cartItem.getPricePerProduct());
        dto.setPrice(cartItem.getPrice());
        return dto;
    }
}
