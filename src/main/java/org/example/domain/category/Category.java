package org.example.domain.category;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("categories")
public class Category {
  @Id
  private String id;
  private String title;
  private String description;
  private String ownerId;

  public Category() {
  }

  public Category(CategoryDTO categoryDTO) {
    this.title = categoryDTO.title();
    this.description = categoryDTO.description();
    this.ownerId = categoryDTO.ownerId();
  }

  @Override
  public String toString() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("title", title);
    json.put("description", description);
    json.put("ownerId", ownerId);
    json.put("type", "categoria");

    return json.toString();
  }

  public String deleteToString() {
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("ownerId", this.ownerId);
    json.put("type", "delete-categoria");

    return json.toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Category category = (Category) o;
    return Objects.equals(getId(), category.getId()) && Objects.equals(getTitle(), category.getTitle()) && Objects.equals(getDescription(), category.getDescription()) && Objects.equals(getOwnerId(), category.getOwnerId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDescription(), getOwnerId());
  }
}
