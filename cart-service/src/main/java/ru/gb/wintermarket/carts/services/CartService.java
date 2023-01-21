package ru.gb.wintermarket.carts.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.gb.wintermarket.api.dto.ProductDto;
import ru.gb.wintermarket.api.exceptions.ResourceNotFoundException;
import ru.gb.wintermarket.carts.integrations.ProductServiceIntegration;
import ru.gb.wintermarket.carts.model.Cart;
import javax.annotation.PostConstruct;
@Slf4j
@Service
@RequiredArgsConstructor
//@Scope("prototype")
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId){
        ProductDto productDto = productServiceIntegration.
                getProductById(productId);
        log.warn("cart 'add method' productDto id: " + productDto.getId()+" "+productDto.getTitle()+"Find!!!");
        tempCart.add(productDto);
    }

    public void increaseProductInCart(Long id) {
       ProductDto productDto = productServiceIntegration.getProductById(id);
        tempCart.increaseProduct(productDto);
    }

    public void decreaseProductInCart(Long id) {
        ProductDto productDto = productServiceIntegration.getProductById(id);
        tempCart.decreaseProduct(productDto);
    }

    public void removeProductById(Long id) {
        ProductDto productDto = productServiceIntegration.getProductById(id);
        tempCart.remove(productDto);
    }

    public void clear() {
        tempCart.clear();
    }
}
