package com.senior.services;

import java.util.List;

import com.senior.dto.request.CartRequest;
import com.senior.dto.response.CartResponse;
import jakarta.validation.OverridesAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senior.entities.Cart;
import com.senior.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findCartById(String id) {
        return this.cartRepository.getById(id);
    }

    @Override
    public List<Cart> getAll() {
        return this.cartRepository.getAll();
    }

    @Override
    public CartResponse create(CartRequest request) {
        Cart cart = request.toEntity();
        Cart createdCart = this.cartRepository.saveWithTransaction(cart);

        return CartResponse.fromEntity(createdCart);
    }

    @Override
    public CartResponse updateCartById(CartRequest request, String id){
        Cart cart = request.toEntity();
        Cart updatedCart = this.cartRepository.updateCart(cart, id);
        return CartResponse.fromEntity(updatedCart);
    }

    @Override
    public CartResponse deleteCartById(CartRequest request, String id) {
        Cart cart = request.toEntity();
        Cart updatedCart = this.cartRepository.deleteCartById(cart, id);
        return CartResponse.fromEntity(updatedCart);
    }

}
