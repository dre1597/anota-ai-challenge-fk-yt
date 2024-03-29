package org.example.controllers;

import org.example.domain.product.Product;
import org.example.domain.product.ProductDTO;
import org.example.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService service;

  public ProductController(final ProductService service) {
    this.service = Objects.requireNonNull(service);
  }

  @PostMapping
  public ResponseEntity<Product> insert(@RequestBody final ProductDTO productData) {
    final var newProduct = this.service.insert(productData);
    return ResponseEntity.ok().body(newProduct);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    final var products = this.service.getAll();
    return ResponseEntity.ok().body(products);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable("id") final String id, @RequestBody final ProductDTO productData) {
    final var product = this.service.update(id, productData);
    return ResponseEntity.ok().body(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
    this.service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
