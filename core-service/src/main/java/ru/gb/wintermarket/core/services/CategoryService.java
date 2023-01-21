package ru.gb.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.wintermarket.core.entity.Category;
import ru.gb.wintermarket.core.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }

    public Category saveCategory(String categoryStr){
        Category category = new Category();
        category.setTitle(categoryStr);
        categoryRepository.save(category);
        return category;
    }
}
