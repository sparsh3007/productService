package com.scaler.productService.controllers;

import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationToken,
                                            @PathVariable Long id) throws NotFoundException {
        // *****************************************************************************************
        // Use the below code to get the product by id from the service.
        //        return productService.getProductById(id);
        // *****************************************************************************************
        //  Below code is to mock the null response from the service and to test the test method in test class.
        System.out.println("Inside ProductController getProductById");
        System.out.println("Still Inside ProductController getProductById");
        if(id==null){
            return new GenericProductDto();
        }
        GenericProductDto genericProductDto= productService.getProductById(id);
        if(genericProductDto==null){
//            throw new NotFoundException("Product with id: "+id+ " not found.");
            return new GenericProductDto();
        }
        /*******************************************************************************************
         The below code is to mock an error in the controller and to test the test method in test class.
         genericProductDto.setTitle("iPhone 13");
         */
        return genericProductDto;
        // *****************************************************************************************
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable Long id,@RequestBody GenericProductDto genericProductDto) throws NotFoundException {
        return productService.updateProductById(id,genericProductDto);
    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) throws NotFoundException {
        return productService.createProduct(genericProductDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable Long id) throws NotFoundException {
        return  new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }
//    -------This is specific to this controller --------
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),HttpStatus.NOT_FOUND);
//    }

}
