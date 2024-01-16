package com.scaler.ecommerceapp.services;

import com.scaler.ecommerceapp.dtos.FakeStoreProductDto;
import com.scaler.ecommerceapp.dtos.GenericProductDto;
import com.scaler.ecommerceapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final String productURL="https://fakestoreapi.com/products/{id}";
    private final String productRequestURL ="https://fakestoreapi.com/products";
    private final RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.getForEntity(productURL, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+id+ " not found.");
        }
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.postForEntity(productRequestURL,product,FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+product.getId()+ " not found.");
        }
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntityArray=restTemplate.getForEntity(productRequestURL, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtosArray= fakeStoreProductDtoResponseEntityArray.getBody();
        List<GenericProductDto> genericProductDtoList=new ArrayList<>();
        for(int i = 0; i< Objects.requireNonNull(fakeStoreProductDtosArray).length; i++){
            GenericProductDto genericProductDto = getGenericProductDto(fakeStoreProductDtosArray[i]);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.exchange(productURL, HttpMethod.DELETE,null,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+id+ " not found.");
        }
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto product) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<GenericProductDto> requestEntity=new HttpEntity<>(product);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.exchange(productURL,HttpMethod.PUT,requestEntity, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+id+ " not found.");
        }
        return getGenericProductDto(fakeStoreProductDto);
    }

    private GenericProductDto getGenericProductDto( FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setRating(fakeStoreProductDto.getRating());
        return genericProductDto;
    }
}
