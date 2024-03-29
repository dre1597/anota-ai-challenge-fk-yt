package org.example.controllers;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryDTO;
import org.example.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService service;

  public CategoryController(final CategoryService service) {
    this.service = Objects.requireNonNull(service);
  }

  @PostMapping
  public ResponseEntity<Category> insert(@RequestBody final CategoryDTO categoryData) {
    final var newCategory = this.service.insert(categoryData);
    return ResponseEntity.ok().body(newCategory);
  }

  @GetMapping
  public ResponseEntity<List<Category>> getAll() {
    final var categories = this.service.getAll();
    return ResponseEntity.ok().body(categories);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> update(@PathVariable("id") final String id, @RequestBody final CategoryDTO categoryData) {
    final var category = this.service.update(id, categoryData);
    return ResponseEntity.ok().body(category);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
    this.service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
