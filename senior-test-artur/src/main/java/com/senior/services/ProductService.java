package com.senior.services;

import com.senior.dto.request.ProductRequest;
import com.senior.dto.response.ProductResponse;
import com.senior.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product findProductById(String id);

    List<Product> getAll();

    ProductResponse create(ProductRequest request);

    ProductResponse updateProductById(ProductRequest request, String id);

    Map<String,String> deleteProductById(ProductRequest request, String id);

    List<Product> getListByType(String type);
}
