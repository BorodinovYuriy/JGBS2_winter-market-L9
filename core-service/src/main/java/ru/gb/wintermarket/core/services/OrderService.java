package ru.gb.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.wintermarket.api.dto.CartDto;
import ru.gb.wintermarket.api.dto.CartItemDto;
import ru.gb.wintermarket.core.entity.Order;
import ru.gb.wintermarket.core.entity.OrderItem;
import ru.gb.wintermarket.core.entity.Product;
import ru.gb.wintermarket.core.integrations.CartServiceIntegration;
import ru.gb.wintermarket.core.repositories.OrderRepository;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String username){
        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        List<OrderItem> orderItemList = new LinkedList<>();

        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());

        for(CartItemDto cartItem : cartDto.getItems()){
            if(productService.findById(cartItem.getProductId()).isPresent()){
                Optional<Product> product = productService.findById(cartItem.getProductId());

                OrderItem orderItem = new OrderItem(
                        null,
                        product.get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice() );

                orderItemList.add(orderItem);
            }
        }
        order.setItems(orderItemList);
        orderRepository.save(order);

        cartServiceIntegration.clearCart();
    }





}
