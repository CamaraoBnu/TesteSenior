package com.senior.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.senior.dto.request.CartProductRequest;
import com.senior.dto.request.CartRequest;
import com.senior.dto.response.CartProductResponse;
import com.senior.dto.response.CartResponse;
import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import com.senior.repositories.CartProductRepository;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,String> addProductToCart(CartProductRequest request, Product product, Cart cart) {

        CartProduct cartProduct = request.toEntity(product, cart);
        Map<String, String> resp= new HashMap<>();

        if (cart.isCartOpen()) {
            if (product.isActive()) {
                CartProduct createdCartProduct = this.cartProductRepository.saveWithTransaction(cartProduct);
                CartProductResponse.fromEntity(createdCartProduct);
                resp.put("response","Cart product added.");
                return resp;
            } else {
                resp.put("response","The product you are trying to add is with status INACTIVE");
                return resp;
            }
        } else {
            resp.put("response","The cart that you are trying to add a product is with status CLOSED.");
            return resp;
        }
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
