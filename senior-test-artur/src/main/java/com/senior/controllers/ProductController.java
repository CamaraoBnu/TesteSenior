package com.senior.controllers;

import com.senior.dto.request.CartRequest;
import com.senior.dto.request.ProductRequest;
import com.senior.dto.response.CartResponse;
import com.senior.dto.response.ProductResponse;
import com.senior.entities.Cart;
import com.senior.entities.Product;
import com.senior.services.CartService;
import com.senior.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/", produces = "application/json", consumes = "application/json")
    public ProductResponse create(@RequestBody ProductRequest body) {
        return productService.create(body);
    }

    @GetMapping(path = "/")
    public List<Product> listAllProducts(){
        return this.productService.getAll();
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public void updateProductById(@RequestBody ProductRequest request, @PathVariable String id){
        this.productService.updateProductById(request, id);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public String deleteProductById(@RequestBody ProductRequest request, @PathVariable String id) {
        return this.productService.deleteProductById(request, id);
    }
}
