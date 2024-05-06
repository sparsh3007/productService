package com.scaler.productService.services;

import com.scaler.productService.Repositories.ProductRepository;
import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.models.Category;
import com.scaler.productService.models.Price;
import com.scaler.productService.models.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        System.out.println("Calling from selfStoreProductService");
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
        }
        Product product1 = optionalProduct.get();
        GenericProductDto genericProductDto = getGenericProductDto(product1);
        return genericProductDto;
       
    }

    private @NotNull GenericProductDto getGenericProductDto(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getUuid().toString());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setRating(product.getRating());
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        product.setCategory(category);
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        price.setCurrency("INR");
        product.setPrice(price);
        product.setRating(genericProductDto.getRating());
        Product savedProduct= productRepository.save(product);

        GenericProductDto savedGenericProductDto = getGenericProductDto(savedProduct);
        return savedGenericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
        }
        Product product = optionalProduct.get();
        GenericProductDto deletedGenericProductDto = getGenericProductDto(product);
        productRepository.deleteById(UUID.fromString(id));
        return deletedGenericProductDto;
    }

    @Override
    public GenericProductDto updateProductById(String id,GenericProductDto genericProductDto) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
        }
        Product product = optionalProduct.get();
        if(genericProductDto.getTitle()!=null){
            product.setTitle(genericProductDto.getTitle());
        }
        if(genericProductDto.getDescription()!=null){
            product.setDescription(genericProductDto.getDescription());
        }
        if(genericProductDto.getImage()!=null){
            product.setImage(genericProductDto.getImage());
        }
        if(genericProductDto.getCategory()!=null){
            Category category = new Category();
            category.setName(genericProductDto.getCategory());
            product.setCategory(category);
        }
        if(genericProductDto.getPrice()!=0.0){
            Price price = new Price();
            price.setPrice(genericProductDto.getPrice());
            price.setCurrency("INR");
            product.setPrice(price);
        }
        if(genericProductDto.getRating()!=null){
            product.setRating(genericProductDto.getRating());
        }
        Product updatedProduct = productRepository.save(product);
        GenericProductDto updatedGenericProductDto = getGenericProductDto(updatedProduct);
        return updatedGenericProductDto;
    }
}
