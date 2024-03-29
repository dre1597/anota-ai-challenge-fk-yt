package org.example.domain.product;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("products")
public class Product {
  @Id
  private String id;
  private String title;
  private String description;
  private String ownerId;
  private Integer price;
  private String category;

  public Product(ProductDTO data) {
    this.title = data.title();
    this.description = data.description();
    this.ownerId = data.ownerId();
    this.price = data.price();
    this.category = data.categoryId();
  }

  public Product() {
  }

  public String toString() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("title", title);
    json.put("description", description);
    json.put("ownerId", ownerId);
    json.put("price", price);
    json.put("category", category);
    json.put("type", "produto");

    return json.toString();
  }

  public String deleteToString() {
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("ownerId", this.ownerId);
    json.put("type", "delete-produto");

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

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Product product = (Product) o;
    return Objects.equals(getId(), product.getId()) && Objects.equals(getTitle(), product.getTitle()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getOwnerId(), product.getOwnerId()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getCategory(), product.getCategory());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDescription(), getOwnerId(), getPrice(), getCategory());
  }
}
