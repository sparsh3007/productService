package com.scaler.productService.services;

import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.models.Price;
import com.scaler.productService.models.Rating;
import com.scaler.productService.thirdpartyclients.fakestore.FakeStoreProductClient;
import com.scaler.productService.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FakeStoreProductServiceImplTest {
    @Autowired
    FakeStoreProductServiceImpl fakeStoreProductService;
    @MockBean
    FakeStoreProductClient fakeStoreProductClient;
    @Test
    public void test_getProductById_when_client_throws_NotFoundException() throws NotFoundException {
        when(fakeStoreProductClient.getProductById(any(Long.class)))
                .thenThrow(new NotFoundException("Product with id:"+  " not found."));
        Assertions.assertThrows(NotFoundException.class,()->fakeStoreProductService.getProductById("10"));
    }
    @Test
    public void test_getProductById_when_client_returns_product() throws NotFoundException {
        FakeStoreProductDto toBeReturnedFakeStoreProductDto=new FakeStoreProductDto();
        toBeReturnedFakeStoreProductDto.setId(1L);
        toBeReturnedFakeStoreProductDto.setCategory("Electronics");
        toBeReturnedFakeStoreProductDto.setTitle("iPhone 12");
        toBeReturnedFakeStoreProductDto.setPrice(1000.0);
        toBeReturnedFakeStoreProductDto.setImage("image");
        toBeReturnedFakeStoreProductDto.setRating(new Rating(4.5,100));
        toBeReturnedFakeStoreProductDto.setDescription("description");

        when(fakeStoreProductClient.getProductById(1L))
                .thenReturn(toBeReturnedFakeStoreProductDto);

        GenericProductDto expectedGenericProductDto=new GenericProductDto();
        expectedGenericProductDto.setId("1");
        expectedGenericProductDto.setCategory("Electronics");
        expectedGenericProductDto.setTitle("iPhone 12");
        expectedGenericProductDto.setPrice(new Price(1000.0,"INR"));
        expectedGenericProductDto.setImage("image");
        expectedGenericProductDto.setInventoryCount(0);
        expectedGenericProductDto.setDescription("description");

        GenericProductDto actualGenericProductDto=fakeStoreProductService.getProductById("1");
        Assertions.assertEquals(expectedGenericProductDto.getId(),actualGenericProductDto.getId());
        Assertions.assertEquals(expectedGenericProductDto.getCategory(),actualGenericProductDto.getCategory());
        Assertions.assertEquals(expectedGenericProductDto.getTitle(),actualGenericProductDto.getTitle());
        Assertions.assertEquals(expectedGenericProductDto.getPrice().getPrice(),actualGenericProductDto.getPrice().getPrice());
        Assertions.assertEquals(expectedGenericProductDto.getPrice().getCurrency(),actualGenericProductDto.getPrice().getCurrency());
        Assertions.assertEquals(expectedGenericProductDto.getImage(),actualGenericProductDto.getImage());
        Assertions.assertEquals(expectedGenericProductDto.getInventoryCount(),actualGenericProductDto.getInventoryCount());
        Assertions.assertEquals(expectedGenericProductDto.getDescription(),actualGenericProductDto.getDescription());

    }
}
