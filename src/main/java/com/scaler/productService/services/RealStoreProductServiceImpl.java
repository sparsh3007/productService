package com.scaler.productService.services;

import com.scaler.productService.Repositories.CategoryRepository;
import com.scaler.productService.Repositories.PriceRespository;
import com.scaler.productService.Repositories.ProductRepository;
import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.dtos.UpdateProductRequestDto;
import com.scaler.productService.dtos.UpdateProductResponseDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.models.Category;
import com.scaler.productService.models.Price;
import com.scaler.productService.models.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Service("selfProductService")
public class RealStoreProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRespository priceRespository;
    @Autowired
    public RealStoreProductServiceImpl(ProductRepository productRepository,
                                       CategoryRepository categoryRepository,
                                       PriceRespository priceRespository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRespository = priceRespository;
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
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setInventoryCount(product.getInventoryCount());
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());

        Optional<Category> optionalCategory=categoryRepository.findByName(genericProductDto.getCategory());
        Category category;
        if(optionalCategory.isEmpty()){
            category = new Category();
            category.setName(genericProductDto.getCategory());
            categoryRepository.save(category);
        }
        else {
            category = optionalCategory.get();
        }
        product.setCategory(category);

        Price price = new Price();
        price.setPrice(genericProductDto.getPrice().getPrice());
        price.setCurrency(genericProductDto.getPrice().getCurrency());
        priceRespository.save(price);

        product.setPrice(price);
        product.setInventoryCount(genericProductDto.getInventoryCount());
        Product savedProduct= productRepository.save(product);

        GenericProductDto savedGenericProductDto = getGenericProductDto(savedProduct);
        return savedGenericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        if(!products.isEmpty()){
            List<GenericProductDto> genericProductDtos = new java.util.ArrayList<>();
            for(Product product:products){
                GenericProductDto genericProductDto = getGenericProductDto(product);
                genericProductDtos.add(genericProductDto);
            }
            return genericProductDtos;
        }
        throw new NotFoundException("No products found.");
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
    public UpdateProductResponseDto updateProductById(String id, UpdateProductRequestDto updateProductRequestDto) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
        }
        Product product = optionalProduct.get();
        if(updateProductRequestDto.getTitle()!=null){
            product.setTitle(updateProductRequestDto.getTitle());
        }
        if(updateProductRequestDto.getDescription()!=null){
            product.setDescription(updateProductRequestDto.getDescription());
        }
        if(updateProductRequestDto.getImage()!=null){
            product.setImage(updateProductRequestDto.getImage());
        }
        if(updateProductRequestDto.getCategory()!=null){
            Category category = new Category();
            category.setName(updateProductRequestDto.getCategory());
            product.setCategory(category);
        }
        if(updateProductRequestDto.getPrice().getPrice()!=0.0){
            Optional<Price> price = priceRespository.findById(product.getPrice().getUuid());
            if(price.isEmpty()){
                throw new NotFoundException("Price not found.");
            }
            Price currentPrice = price.get();
            currentPrice.setPrice(updateProductRequestDto.getPrice().getPrice());
            currentPrice.setCurrency(updateProductRequestDto.getPrice().getCurrency());
            product.setPrice(currentPrice);
        }
        if(updateProductRequestDto.getRating()!=null){
            product.setRating(updateProductRequestDto.getRating());
        }
        if(updateProductRequestDto.getInventoryCount()!=0){
            product.setInventoryCount(updateProductRequestDto.getInventoryCount());
        }
        Product updatedProduct = productRepository.save(product);
        UpdateProductResponseDto updatedProductResponseDto = convertupdatedProductToUpdatedProductResponseDto(updatedProduct);

        return updatedProductResponseDto;
    }

    private @NotNull UpdateProductResponseDto convertupdatedProductToUpdatedProductResponseDto(Product updatedProduct) {
        UpdateProductResponseDto updatedGenericProductDto = new UpdateProductResponseDto();
        updatedGenericProductDto.setId(updatedProduct.getUuid().toString());
        updatedGenericProductDto.setTitle(updatedProduct.getTitle());
        updatedGenericProductDto.setDescription(updatedProduct.getDescription());
        updatedGenericProductDto.setImage(updatedProduct.getImage());
        updatedGenericProductDto.setCategory(updatedProduct.getCategory().getName());
        updatedGenericProductDto.setPrice(updatedProduct.getPrice());
        updatedGenericProductDto.setRating(updatedProduct.getRating());
        updatedGenericProductDto.setInventoryCount(updatedProduct.getInventoryCount());
        return updatedGenericProductDto;
    }
}
