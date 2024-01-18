package com.scaler.ecommerceapp.services;

import com.scaler.ecommerceapp.thirdpartyclients.fakestore.FakeStoreProductClient;
import com.scaler.ecommerceapp.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import com.scaler.ecommerceapp.dtos.GenericProductDto;
import com.scaler.ecommerceapp.exceptions.NotFoundException;
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
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.getProductById(id);
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
    public GenericProductDto deleteProduct(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.deleteProduct(id);
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto genericPoduct) throws NotFoundException {
//       Convert GenericProductDto to FakeStoreProductDto
        FakeStoreProductDto fakeStoreProduct=new FakeStoreProductDto();
        fakeStoreProduct.setId(genericPoduct.getId());
        fakeStoreProduct.setCategory(genericPoduct.getCategory());
        fakeStoreProduct.setTitle(genericPoduct.getTitle());
        fakeStoreProduct.setPrice(genericPoduct.getPrice());
        fakeStoreProduct.setImage(genericPoduct.getImage());
        fakeStoreProduct.setDescription(genericPoduct.getDescription());
        fakeStoreProduct.setRating(genericPoduct.getRating());
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.updateProductById(id,fakeStoreProduct);
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
