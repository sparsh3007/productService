package com.scaler.productService.thirdpartyclients.fakestore;

import com.scaler.productService.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
//    @Value("${fakestore.api.baseurl}")
//    private String fakeStoreApiBaseUrl;
//    @Value("${fakestore.api.product}")
//    private String fakeStoreApiProductsPath;
    private String productURL;
    private String productRequestURL;
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,@Value("${fakestore.api.baseurl}") String fakeStoreApiBaseUrl,
                                  @Value("${fakestore.api.product}") String fakeStoreApiProductsPath){
        this.restTemplateBuilder=restTemplateBuilder;
        productURL=fakeStoreApiBaseUrl+fakeStoreApiProductsPath+"/{uuid}";
        productRequestURL =fakeStoreApiBaseUrl+fakeStoreApiProductsPath;
    }
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.getForEntity(productURL, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+id+ " not found.");
        }
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice().getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory());
        fakeStoreProductDto.setImage(product.getImage());
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.postForEntity(productRequestURL,fakeStoreProductDto,FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDtoResponseEntityBody= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDtoResponseEntityBody==null){
            throw new NotFoundException("Product with uuid: "+product.getId()+ " not found.");
        }
        return fakeStoreProductDtoResponseEntityBody;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntityArray=restTemplate.getForEntity(productRequestURL, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtosArray= fakeStoreProductDtoResponseEntityArray.getBody();

        return Arrays.asList(fakeStoreProductDtosArray);
    }

    public FakeStoreProductDto deleteProduct(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.exchange(productURL, HttpMethod.DELETE,null,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
        }
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto updateProductById(Long id,FakeStoreProductDto product) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<FakeStoreProductDto> requestEntity=new HttpEntity<>(product);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.exchange(productURL,HttpMethod.PUT,requestEntity, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with uuid: "+id+ " not found.");
        }
        return fakeStoreProductDto;
    }
}
