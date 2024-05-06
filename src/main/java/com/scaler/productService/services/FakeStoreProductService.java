package com.scaler.productService.services;

import com.scaler.productService.thirdpartyclients.fakestore.FakeStoreProductClient;
import com.scaler.productService.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Primary // This annotation is used to tell Spring that this is the primary implementation of ProductService
@Service("fakeStoreProductService") // This annotation is used to tell Spring that this is a service class
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient=fakeStoreProductClient;
    }
    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.getProductById(Long.parseLong(id));
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.createProduct(product);
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtoList=fakeStoreProductClient.getAllProducts();
        List<GenericProductDto> genericProductDtoList=new ArrayList<>();
        for(int i = 0; i< Objects.requireNonNull(fakeStoreProductDtoList).size(); i++){
            GenericProductDto genericProductDto = getGenericProductDto(fakeStoreProductDtoList.get(i));
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public GenericProductDto deleteProduct(String id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.deleteProduct(Long.parseLong(id));
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto updateProductById(String id,GenericProductDto genericProduct) throws NotFoundException {
//       Convert GenericProductDto to FakeStoreProductDto
        FakeStoreProductDto fakeStoreProduct=new FakeStoreProductDto();
        fakeStoreProduct.setId(Long.parseLong(genericProduct.getId()));
        fakeStoreProduct.setCategory(genericProduct.getCategory());
        fakeStoreProduct.setTitle(genericProduct.getTitle());
        fakeStoreProduct.setPrice(genericProduct.getPrice());
        fakeStoreProduct.setImage(genericProduct.getImage());
        fakeStoreProduct.setDescription(genericProduct.getDescription());
        fakeStoreProduct.setRating(genericProduct.getRating());
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.updateProductById(Long.parseLong(id),fakeStoreProduct);
        return getGenericProductDto(fakeStoreProductDto);
    }

    private GenericProductDto getGenericProductDto( FakeStoreProductDto fakeStoreProductDto) {
        // This is used to handle the case when the product is not found.
        if(fakeStoreProductDto==null){
            return null;
        }

        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId().toString());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setRating(fakeStoreProductDto.getRating());
        return genericProductDto;
    }
}
