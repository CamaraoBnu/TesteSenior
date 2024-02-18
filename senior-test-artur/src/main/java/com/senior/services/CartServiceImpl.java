package com.senior.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senior.dto.request.CartRequest;
import com.senior.dto.response.CartResponse;
import com.senior.entities.CartProduct;
import org.apache.commons.math3.util.Precision;
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
    public Map<String,Double> getTotalPriceDiscount(CartRequest request) {
        Map<String,Double> resp = new HashMap<>();
        Cart cart = findCartById(request.getId());
        List<CartProduct> cartProduct = cart.getCartProduct();
        double totalProduct = 0;
        double totalService = 0;
        double total = 0;
        for (int i = 0; i < cartProduct.size(); i++) {
            int quantity = cartProduct.get(i).getQuantity();
            if (cartProduct.get(i).getProduct().isProductProductType()) {
                totalProduct = totalProduct + cartProduct.get(i).getProduct().getPrice() * quantity;
            } else {
                totalService = totalService + cartProduct.get(i).getProduct().getPrice() * quantity;
            }
        }

        if(cart.isCartOpen()) {
            totalProduct = ((100 - cart.getDiscount())/100) * totalProduct;
        }
        total = Precision.round(totalProduct + totalService, 2);
        resp.put("Total Pice:",total) ;
        return resp;

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
