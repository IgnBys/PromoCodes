//package com.sii.promoCodes;
//
//import com.sii.promoCodes.Models.Product;
//import com.sii.promoCodes.Models.PromoCode;
//import com.sii.promoCodes.Repositories.PromoCodeRepository;
//import com.sii.promoCodes.Services.ProductService;
//import com.sii.promoCodes.Services.PromoCodeService;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//public class PromoCodeTest {
//
//
//    @Test
//    void tryingToFindPromoCodeByCode() {
//        final PromoCode promoCode = new PromoCode(1L,
//                "SUMMER2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.55),
//                "PLN",
//                5,
//                0);
//
//        final Product product = new Product(1L, "Apple", BigDecimal.valueOf(55.55), "PLN", null);
////        PromoCodeRepository promoCodeRepository = new PromoCodeRepository();
//
//        ProductService productService = new ProductService();
//
//        productService.discountProduct(product.getName(), promoCode.getCode());
//        assertThat(product.getPrice()).isEqualTo(30.00);
////        final PromoCodeRepository promoCodeRepository = null;
////        PromoCode foundPromoCode = promoCodeRepository.findByCode("TESTCODE");
//
//
////        final PromoCodeRepository promoCodeRepository = null;
////        promoCodeRepository.findByCode("SUMMER2024");
////        assertThat(promoCode.getCode()).isEqualTo(foundPromoCode.getCode());
//    }
//
//    @Test
//    void tryingToAddProduct(){
//        final Product product = new Product(1L, "Apple", BigDecimal.valueOf(55.55), "PLN", null);
//
//    }
//}
