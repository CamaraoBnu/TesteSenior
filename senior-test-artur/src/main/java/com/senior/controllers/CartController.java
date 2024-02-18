package com.senior.controllers;

import com.senior.dto.request.CartRequest;
import com.senior.entities.Cart;
import com.senior.enums.CartStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senior.dto.response.CartResponse;
import com.senior.services.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
    public CartResponse create(@Valid @RequestBody CartRequest body, HttpServletRequest request) {
        return cartService.create(body);

    }

    @GetMapping(path = "/getTotal/{id}")
    public Map<String,Double> getTotalPriceDiscount(CartRequest body, @PathVariable String id){
        return this.cartService.getTotalPriceDiscount(body);
    }

    @GetMapping(path = "/list")
    public List<Cart> listAllCarts(){
        return this.cartService.getAll();
    }

    @GetMapping(path = "/listOpen")
    public List<Cart> listAllOpen(){
        return this.cartService.getCartsByStatus(CartStatus.OPEN);
    }

    @GetMapping(path = "/listClosed")
    public List<Cart> listAllClosed(){
        return this.cartService.getCartsByStatus(CartStatus.CLOSED);
    }


    @PutMapping(path = "/update/{id}", produces = "application/json", consumes = "application/json")
    public void updateCartById(@RequestBody CartRequest request, @PathVariable String id){
        this.cartService.updateCartById(request, id);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json", consumes = "application/json")
    public void deleteCartById(@RequestBody CartRequest request, @PathVariable String id) {
        this.cartService.deleteCartById(request, id);
    }


}
