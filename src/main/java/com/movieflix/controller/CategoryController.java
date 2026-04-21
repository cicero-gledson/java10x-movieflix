package com.movieflix.controller;

import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        if (categoryResponses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categoryResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        return optionalCategory.map(category ->
                ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category categorySalvo = categoryService.saveCategory(CategoryMapper.toCategory(categoryRequest));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categorySalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(CategoryMapper.toCategoryResponse(categorySalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
