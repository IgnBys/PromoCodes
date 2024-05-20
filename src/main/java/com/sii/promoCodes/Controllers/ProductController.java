package com.sii.promoCodes.Controllers;

import com.sii.promoCodes.Services.ProductService;
import com.sii.promoCodes.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByname(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        if (product == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return productService.updateProduct(product);
    }

    @PostMapping("/{productName}/{promoCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product discountProduct(@PathVariable String productName, @PathVariable String promoCode) {
        return productService.discountProduct(productName, promoCode);

    }
}