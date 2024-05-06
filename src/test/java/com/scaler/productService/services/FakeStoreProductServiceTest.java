//package com.scaler.productService.services;
//
//import com.scaler.productService.dtos.GenericProductDto;
//import com.scaler.productService.exceptions.NotFoundException;
//import com.scaler.productService.thirdpartyclients.fakestore.FakeStoreProductClient;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class FakeStoreProductServiceTest {
//    @Autowired
//    FakeStoreProductService fakeStoreProductService;
//    @MockBean
//    FakeStoreProductClient fakeStoreProductClient;
//    @Test
//    public void testGetProductByIdWhenClientReturnsNull() throws NotFoundException {
//        when(fakeStoreProductClient.getProductById(any(Long.class)))
//                .thenReturn(null);
//        GenericProductDto genericProductDtoResponse=fakeStoreProductService.getProductById(1L);
//        Assertions.assertNull(genericProductDtoResponse);
//        Assertions.assertThrows(NotFoundException.class,()->fakeStoreProductService.getProductById(1L));
//    }
//}
