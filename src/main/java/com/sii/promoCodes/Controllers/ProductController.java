package com.sii.promoCodes.Controllers;

import com.sii.promoCodes.Models.DiscountResult;
import com.sii.promoCodes.Models.PromoCode;
import com.sii.promoCodes.Services.DiscountResultService;
import com.sii.promoCodes.Services.ProductService;
import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Services.PromoCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private final DiscountResultService discountResultService;

    private final PromoCodeService promoCodeService;

    public ProductController(ProductService productService, DiscountResultService discountResultService, PromoCodeService promoCodeService) {
        this.productService = productService;
        this.discountResultService = discountResultService;
        this.promoCodeService = promoCodeService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable long id ) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return productService.updateProduct(product);
    }

    @PostMapping("/{id}/{promoCodeStr}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DiscountResult> discountProduct(@PathVariable long id, @PathVariable String promoCodeStr) {
        PromoCode promoCode = promoCodeService.getPromoCode(promoCodeStr);
        if (promoCode == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(discountResultService.discountProduct(id, promoCodeStr));

    }
}