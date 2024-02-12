package com.scaler.ecommerceapp.controllers;

import com.scaler.ecommerceapp.dtos.GenericProductDto;
import com.scaler.ecommerceapp.exceptions.NotFoundException;
import com.scaler.ecommerceapp.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
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
    @Test
    public void testGetProductByIdReturnsEmptyObjectWhenProductNotFound() throws NotFoundException {
        when(productServiceMock.getProductById(any(Long.class)))
                .thenReturn(null);
        GenericProductDto genericProductDtoResponse=productController.getProductById(1L);
        Assertions.assertNotNull(genericProductDtoResponse);

    }
    @Test
    public void testGetProductByIdReturnsCorrectObjectWhenProductFound() throws NotFoundException {
        GenericProductDto expectedGenericProductDto=new GenericProductDto();
        expectedGenericProductDto.setId(1L);
        expectedGenericProductDto.setCategory("Electronics");
        expectedGenericProductDto.setTitle("iPhone 12");
        expectedGenericProductDto.setPrice(1000.0);

        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
        toBeReturnedGenericProductDto.setId(1L);
        toBeReturnedGenericProductDto.setCategory("Electronics");
        toBeReturnedGenericProductDto.setTitle("iPhone 12");
        toBeReturnedGenericProductDto.setPrice(1000.0);

        when(productServiceMock.getProductById(any(Long.class)))
                .thenReturn(toBeReturnedGenericProductDto);

        GenericProductDto genericProductDtoResponse=productController.getProductById(1L);

        Assertions.assertEquals(expectedGenericProductDto.getId(),genericProductDtoResponse.getId());
        Assertions.assertEquals(expectedGenericProductDto.getCategory(),genericProductDtoResponse.getCategory());
        Assertions.assertEquals(expectedGenericProductDto.getTitle(),genericProductDtoResponse.getTitle());
        Assertions.assertEquals(expectedGenericProductDto.getPrice(),genericProductDtoResponse.getPrice());
    }
    /*
    private int add(int a, int b) {
        return a + b;
    }
    private Integer add2(int a, int b) {
        return null;
    }
    private void doSomething(){
        throw new NullPointerException();
    }
    @Test
    public void testAdd() {
        int result = add(1, 2);
//        assert add(1, 2) == 3;
        Assertions.assertEquals(3, result,"Some failure happened."); // JUnit 5

        Integer result2=add2(1,2);
        Assertions.assertNull(result2,"Some failure happened too.");

        Assertions.assertThrows(NullPointerException.class,()-> doSomething(),"Value is null");
    }
     */
}