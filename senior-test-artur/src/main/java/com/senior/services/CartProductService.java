package com.senior.services;

import com.senior.dto.request.CartProductRequest;
import com.senior.dto.request.CartRequest;
import com.senior.dto.response.CartProductResponse;
import com.senior.dto.response.CartResponse;
import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;

import java.util.List;

public interface CartProductService {
    CartProduct findCartProductById(String id);

    List<CartProduct> getAll();

    CartProductResponse addProductToCart(CartProductRequest request, Product product, Cart cart);

    CartProductResponse updateCartById(CartProductRequest request, String id);
    CartProductResponse deleteCartById(CartProductRequest request, String id);
}
