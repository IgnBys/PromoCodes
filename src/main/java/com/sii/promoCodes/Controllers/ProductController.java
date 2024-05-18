package com.sii.promoCodes.Controllers;

import com.sii.promoCodes.Models.Purchase;
import com.sii.promoCodes.Services.ProductService;

import com.sii.promoCodes.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByname(@PathVariable String name) {
        return productService.getProductByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(@PathVariable String name, @RequestBody Product product) {
//        product.setName(name);
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/{productName}/{promoCode}")
    public ResponseEntity<Product> discountProduct(@PathVariable String productName, @PathVariable String promoCode) {
        try {
            Product product = productService.discountProduct(productName, promoCode);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}