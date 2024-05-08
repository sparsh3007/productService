package com.scaler.productService.services;

import com.scaler.productService.dtos.GenericProductResponseDto;
import com.scaler.productService.dtos.UpdateProductRequestDto;
import com.scaler.productService.dtos.UpdateProductResponseDto;
import com.scaler.productService.models.Price;
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

//@Primary // This annotation is used to tell Spring that this is the primary implementation of ProductService
@Service("fakeStoreProductService") // This annotation is used to tell Spring that this is a service class
public class FakeStoreProductServiceImpl implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreProductClient fakeStoreProductClient){
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
    public List<GenericProductResponseDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtoList=fakeStoreProductClient.getAllProducts();
        List<GenericProductResponseDto> genericProductResponseDtoList=new ArrayList<>();
        for(int i = 0; i< Objects.requireNonNull(fakeStoreProductDtoList).size(); i++){
            GenericProductResponseDto genericProductResponseDto = getGenericProductResponseDto(fakeStoreProductDtoList.get(i));
            genericProductResponseDtoList.add(genericProductResponseDto);
        }
        return genericProductResponseDtoList;
    }

    private GenericProductResponseDto getGenericProductResponseDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductResponseDto genericProductResponseDto=new GenericProductResponseDto();
        genericProductResponseDto.setId(fakeStoreProductDto.getId().toString());
        genericProductResponseDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductResponseDto.setTitle(fakeStoreProductDto.getTitle());
        Price price=new Price();
        price.setPrice(fakeStoreProductDto.getPrice());
        price.setCurrency("INR");
        genericProductResponseDto.setPrice(price);
        genericProductResponseDto.setImage(fakeStoreProductDto.getImage());
        genericProductResponseDto.setRating(fakeStoreProductDto.getRating());
        return genericProductResponseDto;
    }

    @Override
    public GenericProductDto deleteProduct(String id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.deleteProduct(Long.parseLong(id));
        return getGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public UpdateProductResponseDto updateProductById(String id, UpdateProductRequestDto updateProductRequestDto) throws NotFoundException {
//       Convert GenericProductDto to FakeStoreProductDto
        FakeStoreProductDto fakeStoreProduct= fakeStoreProductClient.getProductById(Long.parseLong(id));

        if (updateProductRequestDto.getPrice()!=null) {
            fakeStoreProduct.setPrice(updateProductRequestDto.getPrice().getPrice());
        }
        if(updateProductRequestDto.getRating()!=null){
            fakeStoreProduct.setRating(updateProductRequestDto.getRating());
        }
        if (updateProductRequestDto.getTitle()!=null) {
            fakeStoreProduct.setTitle(updateProductRequestDto.getTitle());
        }
        if(updateProductRequestDto.getCategory()!=null){
            fakeStoreProduct.setCategory(updateProductRequestDto.getCategory());
        }
        if(updateProductRequestDto.getImage()!=null){
            fakeStoreProduct.setImage(updateProductRequestDto.getImage());
        }
        if(updateProductRequestDto.getDescription()!=null){
            fakeStoreProduct.setDescription(updateProductRequestDto.getDescription());
        }

        FakeStoreProductDto fakeStoreProductDto=fakeStoreProductClient.updateProductById(Long.parseLong(id),fakeStoreProduct);

        UpdateProductResponseDto updateProductResponseDto=new UpdateProductResponseDto();
        updateProductResponseDto.setId(fakeStoreProductDto.getId().toString());
        updateProductResponseDto.setCategory(fakeStoreProductDto.getCategory());
        updateProductResponseDto.setTitle(fakeStoreProductDto.getTitle());
        Price price=new Price();
        price.setPrice(fakeStoreProductDto.getPrice());
        updateProductResponseDto.setPrice(price);
        updateProductResponseDto.setImage(fakeStoreProductDto.getImage());
        updateProductResponseDto.setDescription(fakeStoreProductDto.getDescription());
        return updateProductResponseDto;
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
        Price price=new Price();
        price.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setPrice(price);
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;
    }
}
