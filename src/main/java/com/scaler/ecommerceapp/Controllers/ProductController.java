package com.scaler.ecommerceapp.Controllers;

import com.scaler.ecommerceapp.dtos.GenericProductDto;
import com.scaler.ecommerceapp.exceptions.NotFoundException;
import com.scaler.ecommerceapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService=productService;
    }
    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable Long id) throws NotFoundException {
        return productService.getProductById(id);
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
