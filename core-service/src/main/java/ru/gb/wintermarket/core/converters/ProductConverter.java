package ru.gb.wintermarket.core.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.wintermarket.api.dto.ProductDto;
import ru.gb.wintermarket.core.entity.Category;
import ru.gb.wintermarket.core.entity.Product;
import ru.gb.wintermarket.core.services.CategoryService;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    @Transactional
    public Product dtoToEntity(ProductDto productDto){

        Category category = categoryService.
                findByTitle(productDto.getCategoryTitle()).
                orElse(categoryService.
                        saveCategory(productDto.getCategoryTitle()));

        Product p = new Product();
                p.setId(productDto.getId());
                p.setTitle(productDto.getTitle());
                p.setPrice(productDto.getPrice());
                p.setCategory(category);

        return p;
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory().getTitle()
        );
    }
}

