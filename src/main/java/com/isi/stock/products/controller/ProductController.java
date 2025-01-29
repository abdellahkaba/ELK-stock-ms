package com.isi.stock.products.controller;

import com.isi.stock.products.dto.ProductDtoRequest;
import com.isi.stock.products.dto.ProductDtoResponse;
import com.isi.stock.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> saveProduct(@RequestBody @Valid ProductDtoRequest productDto){
        log.info("Create product: {}", productDto);
        Optional<ProductDtoResponse> productDto1 = productService.saveProduct(productDto);
        return new ResponseEntity<>(productDto1.get(), HttpStatus.CREATED);
    }
    @GetMapping("/{ref}")
    public ResponseEntity<ProductDtoResponse> getProduct(@PathVariable("ref") String ref){
        log.info("Get product by ref: {}", ref);
        Optional<ProductDtoResponse> productDto1 = productService.getProductByRef(ref);
        return new ResponseEntity<>(productDto1.get(), HttpStatus.OK);
    }
}
