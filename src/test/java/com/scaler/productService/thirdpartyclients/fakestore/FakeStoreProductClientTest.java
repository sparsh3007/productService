package com.scaler.productService.thirdpartyclients.fakestore;

import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductClientTest {
    @Autowired
    FakeStoreProductClient fakeStoreProductClient;
    @MockBean
    RestTemplateBuilder restTemplateBuilder;
    @MockBean
    RestTemplate restTemplate;
    @Test
    public void testGetProductById() throws NotFoundException {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=new ResponseEntity<>(null, HttpStatus.OK);
        when(restTemplate.getForEntity(any(String.class),eq(FakeStoreProductDto.class),any(Long.class)))
                .thenReturn(fakeStoreProductDtoResponseEntity);
        FakeStoreProductDto fakeStoreProductDtoResponse=fakeStoreProductClient.getProductById(1L);
        Assertions.assertNull(fakeStoreProductDtoResponse);
    }
}
