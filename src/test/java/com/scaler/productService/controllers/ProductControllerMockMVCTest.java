package com.scaler.productService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.models.Rating;
import com.scaler.productService.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    The below annotation is used to start the spring boot application and to load the context.
 */
//@SpringBootTest
/* The below annotation is used to auto configure the MockMvc instance */
//@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
public class ProductControllerMockMVCTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productServiceMock;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void testGetProductByIDAPI() throws Exception {
        GenericProductDto toBeReturnedGenericProductDto=new GenericProductDto();
        toBeReturnedGenericProductDto.setId(1L);
        toBeReturnedGenericProductDto.setTitle("iPhone 12");
        toBeReturnedGenericProductDto.setDescription("New iPhone 12 with 5G support");
        toBeReturnedGenericProductDto.setImage("iphone12.jpg");
        toBeReturnedGenericProductDto.setCategory("Electronics");
        toBeReturnedGenericProductDto.setPrice(1000.0);
        toBeReturnedGenericProductDto.setRating(new Rating(4.5,1000));
        when(productServiceMock.getProductById(1L))
                .thenReturn(toBeReturnedGenericProductDto);

        ResultActions resultActions = mockMvc.perform(get("/products/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(1));
//                .andExpect(content().json("{\"id\":1,\"category\":\"Electronics\",\"title\":\"iPhone 12\",\"price\":1000.0}"));
        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("{\"id\":1,\"category\":\"Electronics\",\"title\":\"iPhone 12\",\"price\":1000.0,\"description\":\"New iPhone 12 with 5G support\",\"image\":\"iphone12.jpg\",\"rating\":{\"uuid\":null,\"average\":4.5,\"count\":1000}}",responseString);

        /*
          The below code is to test the response using ObjectMapper
         */
        GenericProductDto genericProductDtoResponse=objectMapper.readValue(responseString,GenericProductDto.class);
        /*
          The below code tests if the response is not null
         */
        Assertions.assertNotNull(genericProductDtoResponse);

        /*
          The below code tests if the response is as expected
         */
        Assertions.assertEquals(toBeReturnedGenericProductDto.getId(),genericProductDtoResponse.getId());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getCategory(),genericProductDtoResponse.getCategory());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getTitle(),genericProductDtoResponse.getTitle());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getPrice(),genericProductDtoResponse.getPrice());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getDescription(),genericProductDtoResponse.getDescription());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getImage(),genericProductDtoResponse.getImage());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getRating().getAverage(),genericProductDtoResponse.getRating().getAverage());
        Assertions.assertEquals(toBeReturnedGenericProductDto.getRating().getCount(),genericProductDtoResponse.getRating().getCount());
        Assertions.assertNull(genericProductDtoResponse.getRating().getUuid());

        System.out.println(objectMapper.writeValueAsString(genericProductDtoResponse));
    }
}
