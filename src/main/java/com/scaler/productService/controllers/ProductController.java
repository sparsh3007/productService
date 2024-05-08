package com.scaler.productService.controllers;

import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.dtos.GenericProductResponseDto;
import com.scaler.productService.dtos.UpdateProductRequestDto;
import com.scaler.productService.dtos.UpdateProductResponseDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.security.JwtData;
import com.scaler.productService.security.TokenValidator;
import com.scaler.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    private ProductService productService;
    private TokenValidator tokenValidator;
    @Autowired
    public ProductController(ProductService productService,
                             TokenValidator tokenValidator){
        this.productService=productService;
        this.tokenValidator=tokenValidator;
    }
    @GetMapping()
    public List<GenericProductResponseDto> getAllProducts() throws NotFoundException {

        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable String id) throws NotFoundException { //(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationToken,
                                            //@PathVariable String id) throws NotFoundException {
        // *****************************************************************************************
        // Use the below code to get the product by uuid from the service.
        //        return productService.getProductById(uuid);
        // *****************************************************************************************
        //  Below code is to mock the null response from the service and to test the test method in test class.
//        System.out.println("Inside ProductController getProductById");
//        System.out.println("Still Inside ProductController getProductById");
//
//        Optional<JwtData> jwtData = tokenValidator.validateToken(authorizationToken);
//        if(jwtData.isEmpty()){
//            throw new NotFoundException("Invalid token");
//        }
        if(id==null){
            return new GenericProductDto();
        }
        GenericProductDto genericProductDto= productService.getProductById(id);
        if(genericProductDto==null){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
//            return new GenericProductDto();
        }
        /*******************************************************************************************
         The below code is to mock an error in the controller and to test the test method in test class.
         genericProductDto.setTitle("iPhone 13");
         */
        return genericProductDto;
        // *****************************************************************************************
    }

    @PutMapping("{id}")
    public UpdateProductResponseDto updateProductById(@PathVariable String id, @RequestBody UpdateProductRequestDto updateProductRequestDto) throws NotFoundException {
        return productService.updateProductById(id,updateProductRequestDto);
    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) throws NotFoundException {
        return productService.createProduct(genericProductDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable String id) throws NotFoundException {
        return  new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }
//    -------This is specific to this controller --------
//    Used GlobalExceptionHandler inside exceptions package to handle the NotFoundException instead of handling it here.
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),HttpStatus.NOT_FOUND);
//    }

}
