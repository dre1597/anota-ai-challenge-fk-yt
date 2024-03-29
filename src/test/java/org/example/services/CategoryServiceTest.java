package org.example.services;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryDTO;
import org.example.domain.category.exceptions.CategoryNotFoundException;
import org.example.repositories.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.example.utils.MockCategories.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  @Mock
  private CategoryRepository repository;

  @InjectMocks
  private CategoryService service;

  @Test
  void insertValidData() {
    final var input = new CategoryDTO(TITLE, DESCRIPTION, OWNER_ID);
    final var category = new Category(input);

    given(this.repository.save(category)).willReturn(category);

    final var actual = this.service.insert(input);

    assertEquals(category, actual);
  }

  @Test
  void updateNonexistentCategory() {
    assertThrows(CategoryNotFoundException.class,
        () -> this.service.update("id", null));
  }

  @Test
  @DisplayName("should delete Category when exists")
  void deleteSuccess() {
    final var category = mockCategoryEntity();
    given(this.repository.findById(CATEGORY_ID)).willReturn(java.util.Optional.of(category));
    this.service.delete(CATEGORY_ID);

    then(this.repository).should().delete(category);
  }

  @Test
  @DisplayName("should throw an exception when Category not exists")
  void deleteError() {
    given(this.repository.findById(CATEGORY_ID)).willReturn(Optional.empty());

    assertThrows(CategoryNotFoundException.class, () -> this.service.delete(CATEGORY_ID));
  }

  @Test
  @DisplayName("should return a Category List on getAll")
  void getAllSuccess() {
    given(this.repository.findAll()).willReturn(mockCategoryList());

    final var result = this.service.getAll();

    assertNotNull(result);
    assertEquals(result.get(0).getOwnerId(), OWNER_ID);
    assertEquals(result.get(0).getTitle(), TITLE);
    assertEquals(result.get(0).getDescription(), DESCRIPTION);
  }
}
