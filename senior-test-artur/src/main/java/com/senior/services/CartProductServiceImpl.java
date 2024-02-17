package com.senior.services;

import com.senior.dto.request.CartProductRequest;
import com.senior.dto.request.CartRequest;
import com.senior.dto.response.CartProductResponse;
import com.senior.dto.response.CartResponse;
import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import com.senior.repositories.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService{

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public CartProduct findCartProductById(String id) {
        return this.cartProductRepository.getById(id);
    }

    @Override
    public List<CartProduct> getAll() {
        return this.cartProductRepository.getAll();
    }


    @Override
    public CartProductResponse addProductToCart(CartProductRequest request, Product product, Cart cart) {

        CartProduct cartProduct = request.toEntity(product, cart);

        CartProduct createdCartProduct = this.cartProductRepository.saveWithTransaction(cartProduct);

        return CartProductResponse.fromEntity(createdCartProduct);
    }

    @Override
    public CartProductResponse updateCartById(CartProductRequest request, String id) {
        CartProduct cartProduct = request.toEntity();
        CartProduct updatedCartProduct = this.cartProductRepository.updateCart(cartProduct, id);
        return CartProductResponse.fromEntity(updatedCartProduct);
    }

    @Override
    public CartProductResponse deleteCartById(CartProductRequest request, String id) {
        CartProduct cartProduct = request.toEntity();
        CartProduct updatedCartProduct = this.cartProductRepository.deleteCartById(cartProduct, id);
        return CartProductResponse.fromEntity(updatedCartProduct);
    }
}
