//package com.sii.promoCodes.Services;
//
//import com.sii.promoCodes.Models.Product;
//import com.sii.promoCodes.Models.PromoCode;
//import com.sii.promoCodes.Repositories.ProductRepository;
//import com.sii.promoCodes.Repositories.PromoCodeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class ProductServiceTests {
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ProductService productService;
//
//    @Mock
//    private PromoCodeRepository promoCodeRepository;
//
//    @InjectMocks
//    private PromoCodeService promoCodeService;
//
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void shouldCorrectlySaveTheCreatedProduct() {
//        final Product product = new Product(1L,
//                "Apple",
//                BigDecimal.valueOf(55.55),
//                "PLN",
//                null);
//
//
//        when(productRepository.save(product)).thenReturn(product);
//        Product savedProduct = productService.createProduct(product);
//        assertEquals(product, savedProduct);
//    }
//
//    @Test
//    public void shouldCorrectlyGetAllProductsAndCheckAmountOfThem() {
//        final Product product1 = new Product(1L,
//                "Apple",
//                BigDecimal.valueOf(55.55),
//                "PLN",
//                null);
//
//        final Product product2 = new Product(2L,
//                "Ananas",
//                BigDecimal.valueOf(5.55),
//                "EUR",
//                null);
//
//        final Product product3 = new Product(3L,
//                "Banana",
//                BigDecimal.valueOf(15.55),
//                "USD",
//                null);
//        List<Product> allProducts = Arrays.asList(product1, product2, product3);
//        when(productRepository.findAll()).thenReturn(allProducts);
//        List<Product> allFoundProducts = productService.getAllProducts();
//        assertEquals(allProducts, allFoundProducts);
////        assertEquals(3, allFoundedProducts.size());
//
//    }
//
//    @Test
//    public void shouldCorrectlyFindProductByName() {
//        final Product product = new Product(1L,
//                "Apple",
//                BigDecimal.valueOf(55.55),
//                "PLN",
//                null);
//
//        final Product product2 = new Product(1L,
//                "Banana",
//                BigDecimal.valueOf(55.55),
//                "PLN",
//                null);
//
//        when(productRepository.findByName("Banana")).thenReturn(product2);
//        when(productRepository.findByName("Apple")).thenReturn(product);
//
//        Product foundProduct = productService.getProductByName("Banana");
//        assertEquals(product2, foundProduct);
//    }
//
//    @Test
//    public void shouldCorrectlyUpdateProduct() {
//        final Product product = new Product(1L,
//                "Apple",
//                BigDecimal.valueOf(55.55),
//                "PLN",
//                null);
//
//        final Product newProduct = new Product(1L,
//                "Banana",
//                BigDecimal.valueOf(55.55),
//                "PLN",
//                null);
//
//        when(productRepository.save(any(Product.class))).thenReturn(newProduct);
////        when(productRepository.save(any(Product.class))).thenReturn(any(Product.class));
//
//        Product updatedProduct = productService.updateProduct(newProduct);
////        assertEquals("Banana", updatedProduct.getName());
//
//        assertEquals("Banana", updatedProduct.getName());
//
//    }
//
//
//
//    @Test
//    void shouldCorrectlyDiscountTheProductPriceWithUsingPromoCode() {
//        final PromoCode promoCode = new PromoCode(1L,
//                "SUMMER2024",
//                LocalDateTime.parse("2024-05-30T11:06:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.00),
//                "PLN",
//                5,
//                0);
//
//        Product product = new Product(1L,
//                "Apple",
//                BigDecimal.valueOf(30.00),
//                "PLN",
//                null);
//
//        final Product newProduct = new Product(2L,
//                "Apple",
//                BigDecimal.valueOf(5.00),
//                "PLN",
//                null);
//        when(promoCodeRepository.findByCode(promoCode.getCode())).thenReturn(promoCode);
//        when(productRepository.findByName(product.getName())).thenReturn(product);
//        when(productRepository.save(product)).thenReturn(newProduct);
//        product =  productService.discountProduct(product.getName(), promoCode.getCode());
//        assertEquals(product.getPrice(), BigDecimal.valueOf(5.00));
//    }
//
//
//    @Test
//    void shouldTTest() {
//        final PromoCode promoCode = new PromoCode(1L,
//                "SUMMER2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.55),
//                "PLN",
//                5,
//                0);
//
//        final Product newProduct = new Product(1L,
//                "Apple",
//                BigDecimal.valueOf(30.00),
//                "PLN",
//                null);
//        RuntimeException thrownException = null;
//        try {
//            productService.discountProduct(newProduct.getName(), promoCode.getCode());
//        } catch (RuntimeException e) {
//            thrownException = e;
//        }
//        assertEquals(thrownException.getMessage(), "Incorrect length");
//    }
//
//
//}
