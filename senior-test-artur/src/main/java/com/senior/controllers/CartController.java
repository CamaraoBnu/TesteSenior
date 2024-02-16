package com.senior.controllers;

import com.senior.dto.request.CartRequest;
import com.senior.entities.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senior.dto.response.CartResponse;
import com.senior.services.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @PostMapping(path = "/", produces = "application/json", consumes = "application/json")
    public CartResponse create(@Valid @RequestBody CartRequest body, HttpServletRequest request) {

        CartResponse cartCreated = cartService.create(body);
        return cartCreated;

    }


    @GetMapping(path = "/")
    public List<Cart> listAllCarts(){
        return this.cartService.getAll();
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public void updateCartById(@RequestBody CartRequest request, @PathVariable String id){

        log.info("Updating cart resource", id);
        this.cartService.updateCartById(request, id);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public void deleteCartById(@RequestBody CartRequest request, @PathVariable String id) {
        this.cartService.deleteCartById(request, id);
    }
}
