package org.example.services;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryDTO;
import org.example.domain.category.exceptions.CategoryNotFoundException;
import org.example.repositories.CategoryRepository;
import org.example.services.aws.AwsSnsService;
import org.example.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepository repository;
  private final AwsSnsService snsService;

  public CategoryService(final CategoryRepository repository, final AwsSnsService snsService) {
    this.repository = Objects.requireNonNull(repository);
    this.snsService = Objects.requireNonNull(snsService);
  }

  public Category insert(final CategoryDTO categoryData) {
    final var newCategory = new Category(categoryData);

    this.repository.save(newCategory);
    this.snsService.publish(new MessageDTO(newCategory.toString()));

    return newCategory;
  }

  public Category update(final String id, final CategoryDTO categoryData) {
    final var category = this.repository.findById(id)
        .orElseThrow(CategoryNotFoundException::new);

    if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
    if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

    this.snsService.publish(new MessageDTO(category.toString()));

    this.repository.save(category);

    return category;
  }

  public void delete(final String id) {
    final var category = this.repository.findById(id)
        .orElseThrow(CategoryNotFoundException::new);

    this.repository.delete(category);
    this.snsService.publish(new MessageDTO(category.deleteToString()));
  }

  public List<Category> getAll() {
    return this.repository.findAll();
  }

  public Optional<Category> getById(final String id) {
    return this.repository.findById(id);
  }
}
