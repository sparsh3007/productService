//package com.scaler.productService.controllers;
//
//import com.scaler.productService.dtos.GenericProductDto;
//import com.scaler.productService.dtos.GenericProductResponseDto;
//import com.scaler.productService.exceptions.NotFoundException;
//import com.scaler.productService.models.Price;
//import com.scaler.productService.services.ProductService;
//import org.junit.jupiter.api.*;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//@SpringBootTest
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//public class ProductControllerTest {
//
//    @Autowired
//    ProductController productController;
//    @MockBean
//    ProductService productServiceMock;
//    @Captor
//    private ArgumentCaptor<String> idCaptor;
//    /***************** This is not required as we are using SpringBootTest annotation ***************
//    private ProductControllerTest(){
//        productServiceMock= Mockito.mock(ProductService.class);
//        this.productController=new ProductController(productServiceMock);
//    }
//     */
////    @BeforeAll
////    public static void setup(){
////        System.out.println("Setting up the test");
////    }
//    @Test
//    public void test_getAllProducts_returns_correct_list_of_products() throws NotFoundException {
//        List<GenericProductResponseDto> toBeReturnedGenericProductDtoList=new ArrayList<>();
//        GenericProductResponseDto genericProductDto1=new GenericProductResponseDto();
//        genericProductDto1.setId("1");
//        genericProductDto1.setCategory("Electronics");
//        genericProductDto1.setTitle("iPhone 12");
//        genericProductDto1.setPrice(new Price(1000.0,"INR"));
//        genericProductDto1.setInventoryCount(10);
//        genericProductDto1.setImage("image");
//        genericProductDto1.setDescription("description");
//        toBeReturnedGenericProductDtoList.add(genericProductDto1);
//
//        GenericProductResponseDto genericProductDto2=new GenericProductResponseDto();
//        genericProductDto2.setId("2");
//        genericProductDto2.setCategory("Electronics");
//        genericProductDto2.setTitle("iPhone 12 Pro");
//        genericProductDto2.setPrice(new Price(1200.0,"INR"));
//        genericProductDto2.setInventoryCount(5);
//        genericProductDto2.setImage("image");
//        genericProductDto2.setDescription("description");
//        toBeReturnedGenericProductDtoList.add(genericProductDto2);
//
//        when(productServiceMock.getAllProducts())
//                .thenReturn(toBeReturnedGenericProductDtoList);
//
//        List<GenericProductResponseDto> genericProductDtoListResponse=productController.getAllProducts();
//        Assertions.assertEquals(2,genericProductDtoListResponse.size());
//        for(int i=0;i<genericProductDtoListResponse.size();i++) {
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getId(), genericProductDtoListResponse.get(i).getId());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getCategory(), genericProductDtoListResponse.get(i).getCategory());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getTitle(), genericProductDtoListResponse.get(i).getTitle());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getPrice().getPrice(), genericProductDtoListResponse.get(i).getPrice().getPrice());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getPrice().getCurrency(), genericProductDtoListResponse.get(i).getPrice().getCurrency());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getInventoryCount(), genericProductDtoListResponse.get(i).getInventoryCount());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getImage(), genericProductDtoListResponse.get(i).getImage());
//            Assertions.assertEquals(toBeReturnedGenericProductDtoList.get(i).getDescription(), genericProductDtoListResponse.get(i).getDescription());
//        }
//
//    }
//
//    @Test
//    public void test_getProductById_throws_NotFoundException_when_product_is_not_found() throws NotFoundException {
//
//
//        when(productServiceMock.getProductById("2"))
//                .thenReturn(null);
//        Assertions.assertThrows(NotFoundException.class,()->productController.getProductById("2"));
//    }
//
//    @Test
//    public void test_getProductById_returns_correct_object_when_product_is_found() throws NotFoundException {
//
//        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
//        toBeReturnedGenericProductDto.setId("e06b9986-e3ac-4694-97b5-8c170b8e7a5e");
//        toBeReturnedGenericProductDto.setCategory("Electronics");
//        toBeReturnedGenericProductDto.setTitle("iPhone 12");
//        toBeReturnedGenericProductDto.setPrice(new Price(1000.0,"INR"));
//        toBeReturnedGenericProductDto.setInventoryCount(10);
//        toBeReturnedGenericProductDto.setImage("image");
//        toBeReturnedGenericProductDto.setDescription("description");
//
//        GenericProductDto expectedGenericProductDto=new GenericProductDto();
//        expectedGenericProductDto.setId("e06b9986-e3ac-4694-97b5-8c170b8e7a5e");
//        expectedGenericProductDto.setCategory("Electronics");
//        expectedGenericProductDto.setTitle("iPhone 12");
//        expectedGenericProductDto.setPrice(new Price(1000.0,"INR"));
//        expectedGenericProductDto.setInventoryCount(10);
//        expectedGenericProductDto.setImage("image");
//        expectedGenericProductDto.setDescription("description");
//
//        when(productServiceMock.getProductById("e06b9986-e3ac-4694-97b5-8c170b8e7a5e"))
//                .thenReturn(toBeReturnedGenericProductDto);
//
//        GenericProductDto genericProductDtoResponse=productController.getProductById("e06b9986-e3ac-4694-97b5-8c170b8e7a5e");
//
//        Assertions.assertEquals(expectedGenericProductDto.getId(),genericProductDtoResponse.getId());
//        Assertions.assertEquals(expectedGenericProductDto.getCategory(),genericProductDtoResponse.getCategory());
//        Assertions.assertEquals(expectedGenericProductDto.getTitle(),genericProductDtoResponse.getTitle());
//        Assertions.assertEquals(expectedGenericProductDto.getPrice().getPrice(),genericProductDtoResponse.getPrice().getPrice());
//        Assertions.assertEquals(expectedGenericProductDto.getPrice().getCurrency(),genericProductDtoResponse.getPrice().getCurrency());
//        Assertions.assertEquals(expectedGenericProductDto.getInventoryCount(),genericProductDtoResponse.getInventoryCount());
//        Assertions.assertEquals(expectedGenericProductDto.getImage(),genericProductDtoResponse.getImage());
//        Assertions.assertEquals(expectedGenericProductDto.getDescription(),genericProductDtoResponse.getDescription());
//    }
//    // The below test is an example of mocking whereas the above tests are examples of stubbing.
//    // We are testing if the getProductById method is called exactly once with the argument "1"
//    @Test
//    public void test_GetProductById_using_Mock() throws NotFoundException {
//        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
//        toBeReturnedGenericProductDto.setId("1");
//        toBeReturnedGenericProductDto.setCategory("Electronics");
//        toBeReturnedGenericProductDto.setTitle("iPhone 12");
//        toBeReturnedGenericProductDto.setPrice(new Price(1000.0,"INR"));
//        toBeReturnedGenericProductDto.setInventoryCount(10);
//        toBeReturnedGenericProductDto.setImage("image");
//        toBeReturnedGenericProductDto.setDescription("description");
//        when(productServiceMock.getProductById(any(String.class)))
//                .thenReturn(toBeReturnedGenericProductDto);
//        GenericProductDto genericProductDtoResponse=productController.getProductById("1");
//        verify(productServiceMock,times(1)).getProductById("1");
//    }
//    @Test
//    @DisplayName("Test getProductById method in ProductController using ArgumentCaptor")
//    public void testGetProductByIdCheckId() throws NotFoundException {
//        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
//        toBeReturnedGenericProductDto.setId("1");
//        toBeReturnedGenericProductDto.setCategory("Electronics");
//        toBeReturnedGenericProductDto.setTitle("iPhone 12");
//        toBeReturnedGenericProductDto.setPrice(new Price(1000.0,"INR"));
//        toBeReturnedGenericProductDto.setInventoryCount(10);
//        toBeReturnedGenericProductDto.setImage("image");
//        toBeReturnedGenericProductDto.setDescription("description");
//        when(productServiceMock.getProductById(any(String.class)))
//                .thenReturn(toBeReturnedGenericProductDto);
//        GenericProductDto genericProductDtoResponse=productController.getProductById("1");
//        verify(productServiceMock)
//                .getProductById(idCaptor.capture());
//        String capturedId=idCaptor.getValue();
//        Assertions.assertEquals("1",capturedId);
//    }
//    @Test
//    public void test_getAllProducts_using_Mock() throws NotFoundException {
//        productController.getAllProducts();
//        verify(productServiceMock,times(1)).getAllProducts();
//    }
//}
