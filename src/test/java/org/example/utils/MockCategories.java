package org.example.utils;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class MockCategories {
  public static String TITLE = "ANY TITLE";
  public static String DESCRIPTION = "ANY DESCRIPTION";
  public static String OWNER_ID = "ANY OWNER ID";
  public static String CATEGORY_ID = "ANY CATEGORY ID";

  public static CategoryDTO mockCategoryDTO() {
    return new CategoryDTO(TITLE, DESCRIPTION, OWNER_ID);
  }

  public static Category mockCategoryEntity() {
    return new Category(mockCategoryDTO());
  }

  public static List<Category> mockCategoryList() {
    final var category = mockCategoryEntity();
    final var listCategories = new ArrayList<Category>();
    listCategories.add(category);
    return listCategories;
  }
}
