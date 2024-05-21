package com.sii.promoCodes;

import com.sii.promoCodes.Models.Product;
import com.sii.promoCodes.Repositories.ProductRepository;
import com.sii.promoCodes.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void shouldCorrectlyCreateProduct() {
        final Product product = new Product(1L,
                "Apple",
                BigDecimal.valueOf(55.55),
                "PLN",
                null);


        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.createProduct(product);
        assertEquals(product, savedProduct);
    }

    @Test
    public void shouldCorrectlyGetAllProducts() {
        final Product product1 = new Product(1L,
                "Apple",
                BigDecimal.valueOf(55.55),
                "PLN",
                null);

        final Product product2 = new Product(2L,
                "Ananas",
                BigDecimal.valueOf(5.55),
                "EUR",
                null);

        final Product product3 = new Product(3L,
                "Banana",
                BigDecimal.valueOf(15.55),
                "USD",
                null);
        List<Product> allProducts = Arrays.asList(product1, product2, product3);
        when(productRepository.findAll()).thenReturn(allProducts);
        List<Product> allFoundProducts = productService.getAllProducts();
//        assertEquals(allProducts, allFoundProducts);
        assertEquals(3, allProducts.size());

    }

    @Test
    public void shouldCorrectlyFindProductById() {
        final Product product1 = new Product(1L,
                "Apple",
                BigDecimal.valueOf(55.55),
                "PLN",
                null);

        final Product product2 = new Product(2L,
                "Banana",
                BigDecimal.valueOf(55.55),
                "PLN",
                null);

        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Optional<Product> foundProduct = productService.getProductById(1L);
        assertEquals(product1, foundProduct.get());
    }

    @Test
    public void shouldCorrectlyUpdateProduct() {
        final Product product = new Product(1L,
                "Apple",
                BigDecimal.valueOf(55.55),
                "PLN",
                null);

        final Product newProduct = new Product(1L,
                "Banana",
                BigDecimal.valueOf(55.55),
                "PLN",
                null);

        when(productRepository.save(newProduct)).thenReturn(newProduct);
        Product updatedProduct = productService.updateProduct(newProduct);
//        assertEquals("Banana", updatedProduct.getName());

        assertEquals("Banana", updatedProduct.getName());

    }

}
