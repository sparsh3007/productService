//package com.scaler.productService.controllers;
//
//import com.scaler.productService.dtos.GenericProductDto;
//import com.scaler.productService.dtos.SearchRequestDto;
//import com.scaler.productService.services.SearchService;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/search")
//public class SearchController {
//
//    private SearchService searchService;
//    public SearchController(SearchService searchService) {
//        this.searchService = searchService;
//    }
//    @PostMapping
//    public Page<GenericProductDto> search(@RequestBody SearchRequestDto searchRequestDto) {
//        return searchService.searchProducts(searchRequestDto.getQuery(),searchRequestDto.getPageNumber(),searchRequestDto.getPageSize());
//    }
//}
