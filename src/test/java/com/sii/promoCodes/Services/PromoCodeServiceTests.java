//package com.sii.promoCodes.Services;
//import com.sii.promoCodes.Models.PromoCode;
//import com.sii.promoCodes.Repositories.PromoCodeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class PromoCodeServiceTests {
//    @Mock
//    private PromoCodeRepository promoCodeRepository;
//
//    @InjectMocks
//    private PromoCodeService promoCodeService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void shouldCorrectlySaveTheCreatedPromoCode() {
//        final PromoCode promoCode = new PromoCode(1L,
//                "SUMMER2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.55),
//                "PLN",
//                5,
//                0);
//
//        when(promoCodeRepository.save(promoCode)).thenReturn(promoCode);
//        PromoCode savedPromoCode = promoCodeService.createPromoCode(promoCode);
//        assertEquals(promoCode, savedPromoCode);
//    }
//
//    @Test
//    public void shouldCorrectlyFindPromoCodeByCode() {
//        final PromoCode promoCode = new PromoCode(1L,
//                "SUMMER2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.55),
//                "PLN",
//                5,
//                0);
//
//        when(promoCodeRepository.findByCode("SUMMER2024")).thenReturn(promoCode);
//        PromoCode foundPromoCode = promoCodeService.getPromoCode("SUMMER2024");
//        assertEquals("SUMMER2024", foundPromoCode.getCode());
//    }
//
//    @Test
//    public void shouldCorrectlyFindAllPromoCodesAndCheckAmountOfThem() {
//        final PromoCode promoCode1 = new PromoCode(1L,
//                "SUMMER2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.55),
//                "PLN",
//                5,
//                0);
//        final PromoCode promoCode2 = new PromoCode(2L,
//                "WINTER2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(2.55),
//                "EUR",
//                5,
//                0);
//
//        final PromoCode promoCode3 = new PromoCode(3L,
//                "SPRING2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(3.55),
//                "PLN",
//                5,
//                0);
//
//        List<PromoCode> allPromoCodes = Arrays.asList(promoCode1, promoCode2, promoCode3);
//        when(promoCodeRepository.findAll()).thenReturn(allPromoCodes);
//        List<PromoCode> allFoundPromoCodes = promoCodeService.getAllPromoCodes();
////        assertEquals(allPromoCodes, allFoundPromoCodes);
//        assertEquals(3, allFoundPromoCodes.size());
//    }
//
//    @Test
//    void shouldReturnIncorrectLengthPromoCodeExceptionMessage() {
//        final PromoCode promoCode = new PromoCode(1L,
//                "SUMMERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR2024",
//                LocalDateTime.parse("2024-05-30T11:06:18", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
//                LocalDateTime.now(),
//                BigDecimal.valueOf(25.55),
//                "PLN",
//                5,
//                0);
//
//        RuntimeException thrownException = null;
//        try {
//            promoCodeService.createPromoCode(promoCode);
//        } catch (RuntimeException e) {
//            thrownException = e;
//        }
//        assertEquals(thrownException.getMessage(), "Incorrect length");
//    }
//
//}
