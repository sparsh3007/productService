//package com.scaler.productService.controllers;
//
//import com.scaler.productService.dtos.GenericProductDto;
//import com.scaler.productService.exceptions.NotFoundException;
//import com.scaler.productService.services.ProductService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//@SpringBootTest
//public class ProductControllerTest {
//
//    @Autowired
//    ProductController productController;
//    @MockBean
//    ProductService productServiceMock;
//    /***************** This is not required as we are using SpringBootTest annotation ***************
//    private ProductControllerTest(){
//        productServiceMock= Mockito.mock(ProductService.class);
//        this.productController=new ProductController(productServiceMock);
//    }
//     */
//    @Captor
//    private ArgumentCaptor<Long> idCaptor;
//    @Test
//    public void testGetProductByIdReturnsEmptyObjectWhenProductNotFound() throws NotFoundException {
//        when(productServiceMock.getProductById(any(Long.class)))
//                .thenReturn(null);
//        GenericProductDto genericProductDtoResponse=productController.getProductById(1L);
//        Assertions.assertNotNull(genericProductDtoResponse);
//
//    }
//    @Test
//    public void testGetProductByIdReturnsCorrectObjectWhenProductFound() throws NotFoundException {
//        GenericProductDto expectedGenericProductDto=new GenericProductDto();
//        expectedGenericProductDto.setUuid(1L);
//        expectedGenericProductDto.setCategory("Electronics");
//        expectedGenericProductDto.setTitle("iPhone 12");
//        expectedGenericProductDto.setPrice(1000.0);
//
//        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
//        toBeReturnedGenericProductDto.setUuid(1L);
//        toBeReturnedGenericProductDto.setCategory("Electronics");
//        toBeReturnedGenericProductDto.setTitle("iPhone 12");
//        toBeReturnedGenericProductDto.setPrice(1000.0);
//
//        when(productServiceMock.getProductById(any(Long.class)))
//                .thenReturn(toBeReturnedGenericProductDto);
//
//        GenericProductDto genericProductDtoResponse=productController.getProductById(1L);
//
//        Assertions.assertEquals(expectedGenericProductDto.getUuid(),genericProductDtoResponse.getUuid());
//        Assertions.assertEquals(expectedGenericProductDto.getCategory(),genericProductDtoResponse.getCategory());
//        Assertions.assertEquals(expectedGenericProductDto.getTitle(),genericProductDtoResponse.getTitle());
//        Assertions.assertEquals(expectedGenericProductDto.getPrice(),genericProductDtoResponse.getPrice());
//    }
//    // The below test is an example of mocking whereas the above tests are examples of stubbing.
//    // We are testing the behaviour of the controller when the service returns null.
//    @Test
//    public void testGetProductByIdUsingMock() throws NotFoundException {
//        when(productServiceMock.getProductById(any(Long.class)))
//                .thenReturn(null);
//        GenericProductDto genericProductDtoResponse=productController.getProductById(null);
//        verify(productServiceMock,times(0)).getProductById(null);
//    }
//    @Test
//    @DisplayName("Test getProductById method in ProductController using ArgumentCaptor")
//    public void testGetProductByIdCheckId() throws NotFoundException {
//        when(productServiceMock.getProductById(any(Long.class)))
//                .thenReturn(null);
//        GenericProductDto genericProductDtoResponse=productController.getProductById(1L);
//        verify(productServiceMock).getProductById(idCaptor.capture());
//        Long capturedId=idCaptor.getValue();
//        Assertions.assertEquals(1L,capturedId);
//    }
//    /*
//    private int add(int a, int b) {
//        return a + b;
//    }
//    private Integer add2(int a, int b) {
//        return null;
//    }
//    private void doSomething(){
//        throw new NullPointerException();
//    }
//    @Test
//    public void testAdd() {
//        int result = add(1, 2);
////        assert add(1, 2) == 3;
//        Assertions.assertEquals(3, result,"Some failure happened."); // JUnit 5
//
//        Integer result2=add2(1,2);
//        Assertions.assertNull(result2,"Some failure happened too.");
//
//        Assertions.assertThrows(NullPointerException.class,()-> doSomething(),"Value is null");
//    }
//     */
//}
