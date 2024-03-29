package com.senior.services;

import java.util.List;
import java.util.Map;

import com.senior.dto.request.CartRequest;
import com.senior.dto.response.CartResponse;
import com.senior.entities.Cart;
import com.senior.enums.CartStatus;

public interface CartService {
    Cart findCartById(String id);

    List<Cart> getAll();

    CartResponse create(CartRequest request);

    CartResponse updateCartById(CartRequest request, String id);
    CartResponse deleteCartById(CartRequest request, String id);

    Map<String,Double> getTotalPriceDiscount(CartRequest request);

    List<Cart> getCartsByStatus(CartStatus status);
}
