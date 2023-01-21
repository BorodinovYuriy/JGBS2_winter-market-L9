package ru.gb.wintermarket.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.wintermarket.api.dto.ProductDto;
import ru.gb.wintermarket.api.exceptions.ResourceNotFoundException;
import ru.gb.wintermarket.core.converters.ProductConverter;
import ru.gb.wintermarket.core.entity.Product;
import ru.gb.wintermarket.core.services.ProductService;
import ru.gb.wintermarket.core.validators.ProductValidator;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    private final ProductValidator productValidator;

    //**************************
    //Основной метод по возвращению фильтрованного списка dto сущностей!
    //Доступ auth
    @GetMapping()
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ){
        if(page < 1){page = 1;}
        return productService.getProducts(minPrice, maxPrice, titlePart, page).map(
                productConverter::entityToDto
        );
    }
    //**************************
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Продукт не найден, id: "+ id));
        ProductDto pDto = productConverter.entityToDto(p);
        log.warn("ProductDto findProductById: " + pDto.getTitle() + " " + pDto.getId());
        return pDto;
    }
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
         productService.deleteById(id);
    }



    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }
    @PostMapping()
    public Page<ProductDto> saveNewProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        productDto.setId(null);
        productService.saveProduct(productConverter.dtoToEntity(productDto));
        return getAllProducts(1, null,null, null);
    }

    @PostMapping("/test")
    public Product saveTest (@RequestBody Product product){
        productService.saveProduct(product);
        return productService.findByTitle(product.getTitle());
    }























}
