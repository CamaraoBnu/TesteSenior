package com.senior.services;

import com.senior.dto.request.ProductRequest;
import com.senior.dto.response.ProductResponse;
import com.senior.entities.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(String id);

    List<Product> getAll();

    ProductResponse create(ProductRequest request);

    ProductResponse updateProductById(ProductRequest request, String id);

    ProductResponse deleteProductById(ProductRequest request, String id);
}
