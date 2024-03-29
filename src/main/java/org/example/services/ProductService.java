package org.example.services;

import org.example.domain.category.exceptions.CategoryNotFoundException;
import org.example.domain.product.Product;
import org.example.domain.product.ProductDTO;
import org.example.repositories.ProductRepository;
import org.example.services.aws.AwsSnsService;
import org.example.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
  private final CategoryService categoryService;
  private final ProductRepository productRepository;
  private final AwsSnsService snsService;


  public ProductService(final CategoryService categoryService, final ProductRepository productRepository, final AwsSnsService snsService) {
    this.categoryService = Objects.requireNonNull(categoryService);
    this.productRepository = Objects.requireNonNull(productRepository);
    this.snsService = Objects.requireNonNull(snsService);
  }

  public Product insert(final ProductDTO productData) {
    this.categoryService.getById(productData.categoryId())
        .orElseThrow(CategoryNotFoundException::new);

    final var newProduct = new Product(productData);
    this.productRepository.save(newProduct);
    this.snsService.publish(new MessageDTO(newProduct.toString()));
    return newProduct;
  }

  public Product update(final String id, final ProductDTO productData) {
    final var product = this.productRepository.findById(id)
        .orElseThrow(CategoryNotFoundException::new);

    this.categoryService.getById(productData.categoryId())
        .orElseThrow(CategoryNotFoundException::new);

    if (!productData.title().isEmpty()) product.setTitle(productData.title());
    if (!productData.description().isEmpty()) product.setDescription(productData.description());
    if (!productData.price().equals(0)) product.setPrice(productData.price());
    if (!productData.categoryId().isEmpty()) product.setCategory(productData.categoryId());

    this.productRepository.save(product);
    this.snsService.publish(new MessageDTO(product.toString()));

    return product;
  }

  public void delete(final String id) {
    final var product = this.productRepository.findById(id)
        .orElseThrow(CategoryNotFoundException::new);

    this.productRepository.delete(product);
    this.snsService.publish(new MessageDTO(product.deleteToString()));
  }

  public List<Product> getAll() {
    return this.productRepository.findAll();
  }
}
