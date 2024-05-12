package com.scaler.productService.controllers;

import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.models.Price;
import com.scaler.productService.services.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductControllerTest {

    @Autowired
    ProductController productController;
    @MockBean
    ProductService productServiceMock;
    /***************** This is not required as we are using SpringBootTest annotation ***************
    private ProductControllerTest(){
        productServiceMock= Mockito.mock(ProductService.class);
        this.productController=new ProductController(productServiceMock);
    }
     */
//    @BeforeAll
//    public static void setup(){
//        System.out.println("Setting up the test");
//    }
//    @Captor
//    private ArgumentCaptor<Long> idCaptor;
    @Test
    public void test_getProductById_throws_NotFoundException_when_product_is_not_found() throws NotFoundException {


        when(productServiceMock.getProductById("2"))
                .thenReturn(null);
        Assertions.assertThrows(NotFoundException.class,()->productController.getProductById("2"));
    }

    @Test
    public void test_getProductById_returns_correct_object_when_product_is_found() throws NotFoundException {

        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
        toBeReturnedGenericProductDto.setId("e06b9986-e3ac-4694-97b5-8c170b8e7a5e");
        toBeReturnedGenericProductDto.setCategory("Electronics");
        toBeReturnedGenericProductDto.setTitle("iPhone 12");
        toBeReturnedGenericProductDto.setPrice(new Price(1000.0,"INR"));
        toBeReturnedGenericProductDto.setInventoryCount(10);
        toBeReturnedGenericProductDto.setImage("image");
        toBeReturnedGenericProductDto.setDescription("description");

        GenericProductDto expectedGenericProductDto=new GenericProductDto();
        expectedGenericProductDto.setId("e06b9986-e3ac-4694-97b5-8c170b8e7a5e");
        expectedGenericProductDto.setCategory("Electronics");
        expectedGenericProductDto.setTitle("iPhone 12");
        expectedGenericProductDto.setPrice(new Price(1000.0,"INR"));
        expectedGenericProductDto.setInventoryCount(10);
        expectedGenericProductDto.setImage("image");
        expectedGenericProductDto.setDescription("description");

        when(productServiceMock.getProductById("e06b9986-e3ac-4694-97b5-8c170b8e7a5e"))
                .thenReturn(toBeReturnedGenericProductDto);

        GenericProductDto genericProductDtoResponse=productController.getProductById("e06b9986-e3ac-4694-97b5-8c170b8e7a5e");

        Assertions.assertEquals(expectedGenericProductDto.getId(),genericProductDtoResponse.getId());
        Assertions.assertEquals(expectedGenericProductDto.getCategory(),genericProductDtoResponse.getCategory());
        Assertions.assertEquals(expectedGenericProductDto.getTitle(),genericProductDtoResponse.getTitle());
        Assertions.assertEquals(expectedGenericProductDto.getPrice().getPrice(),genericProductDtoResponse.getPrice().getPrice());
        Assertions.assertEquals(expectedGenericProductDto.getPrice().getCurrency(),genericProductDtoResponse.getPrice().getCurrency());
        Assertions.assertEquals(expectedGenericProductDto.getInventoryCount(),genericProductDtoResponse.getInventoryCount());
        Assertions.assertEquals(expectedGenericProductDto.getImage(),genericProductDtoResponse.getImage());
        Assertions.assertEquals(expectedGenericProductDto.getDescription(),genericProductDtoResponse.getDescription());
    }
    // The below test is an example of mocking whereas the above tests are examples of stubbing.
    // We are testing the behaviour of the controller when the service returns null.
    @Test
    public void testGetProductByIdUsingMock() throws NotFoundException {
        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
        toBeReturnedGenericProductDto.setId("1");
        toBeReturnedGenericProductDto.setCategory("Electronics");
        toBeReturnedGenericProductDto.setTitle("iPhone 12");
        toBeReturnedGenericProductDto.setPrice(new Price(1000.0,"INR"));
        toBeReturnedGenericProductDto.setInventoryCount(10);
        toBeReturnedGenericProductDto.setImage("image");
        toBeReturnedGenericProductDto.setDescription("description");
        when(productServiceMock.getProductById(any(String.class)))
                .thenReturn(toBeReturnedGenericProductDto);
        GenericProductDto genericProductDtoResponse=productController.getProductById("1");
        verify(productServiceMock,times(1)).getProductById("1");
    }
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
}
