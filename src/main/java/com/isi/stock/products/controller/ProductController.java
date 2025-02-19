package com.isi.stock.products.controller;

import com.isi.stock.products.dto.ProductDtoRequest;
import com.isi.stock.products.dto.ProductDtoResponse;
import com.isi.stock.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Getter
@Setter
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> saveProduct(@RequestBody @Valid ProductDtoRequest productDto){
        Optional<ProductDtoResponse> productDto1 = productService.saveProduct(productDto);
        return new ResponseEntity<>(productDto1.get(), HttpStatus.CREATED);
    }
    @GetMapping("/{ref}")
    public ResponseEntity<ProductDtoResponse> getProduct(@PathVariable("ref") String ref){
        Optional<ProductDtoResponse> productDto1 = productService.getProductByRef(ref);
        return new ResponseEntity<>(productDto1.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> allProducts(){
        Optional<List<ProductDtoResponse>> productDtos = productService.getAllProducts();
        return new ResponseEntity<>(productDtos.get(), HttpStatus.OK);
    }

    @PutMapping("/{ref}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable("ref") String ref, @RequestBody @Valid ProductDtoRequest productDto){
        Optional<ProductDtoResponse> productDto1 = productService.updateProduct(ref, productDto);
        return new ResponseEntity<>(productDto1.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{ref}")
    public ResponseEntity<ProductDtoResponse> deleteProduct(@PathVariable("ref") String ref){
        productService.deleteProduct(ref);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
