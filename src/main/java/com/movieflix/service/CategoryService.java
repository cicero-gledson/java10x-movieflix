package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.repository.CategoryRepository;
import com.movieflix.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
