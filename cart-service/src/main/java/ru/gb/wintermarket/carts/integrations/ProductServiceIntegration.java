package ru.gb.wintermarket.carts.integrations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.wintermarket.api.dto.ProductDto;
import ru.gb.wintermarket.api.exceptions.ResourceNotFoundException;
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id) {
        log.warn("try to find "+id);
    return productServiceWebClient.get()
            .uri("/api/v1/products/" + id)
            .retrieve()//мы хотим получить ответ
            .onStatus(
                    httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                    clientResponse -> Mono.error(new ResourceNotFoundException("ProductServiceIntegration: Товар не найден в продуктовом МС"))
                    )
            .bodyToMono(ProductDto.class)//хотим тело преобразовать к объекту//пока синхронно (не flux)
            .block();
    }

}
