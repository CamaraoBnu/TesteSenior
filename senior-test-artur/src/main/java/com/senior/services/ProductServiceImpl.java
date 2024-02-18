package com.senior.services;

import com.senior.controllers.CartProductController;
import com.senior.dto.request.CartRequest;
import com.senior.dto.request.ProductRequest;
import com.senior.dto.response.CartResponse;
import com.senior.dto.response.ProductResponse;
import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import com.senior.repositories.CartProductRepository;
import com.senior.repositories.CartRepository;
import com.senior.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public Product findProductById(String id) {
        return this.productRepository.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = request.toEntity();
        Product createdProduct = this.productRepository.saveWithTransaction(product);
        return ProductResponse.fromEntity(createdProduct);
    }

    @Override
    public ProductResponse updateProductById(ProductRequest request, String id) {

        Product product = request.toEntity();
        Product updatedProduct = this.productRepository.updateCart(product, id);
        return ProductResponse.fromEntity(updatedProduct);
    }

    @Override
    public String deleteProductById(ProductRequest request, String id) {
        Product product = request.toEntity();
        List<CartProduct> list = this.cartProductRepository.getAll();
        int countHas = 0;
        for (int i = 0; i < list.size(); i++) {
           if(list.get(i).getProduct().getId().equals(id)) {
               countHas++;
           }
        }
        if (countHas > 0) {
            return "Product already attached to a cart. Exclusion Denied.";
        }

        Product updatedProduct = this.productRepository.deleteCartById(product, id);
        ProductResponse.fromEntity(updatedProduct);
        return "Product Deleted.";
    }


}
