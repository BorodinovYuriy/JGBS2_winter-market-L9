package ru.gb.wintermarket.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.wintermarket.api.dto.CartDto;
import ru.gb.wintermarket.carts.model.Cart;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;
    public CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().
                map(cartItemConverter::entityToDto).
                toList());
        return cartDto;
    }

}
