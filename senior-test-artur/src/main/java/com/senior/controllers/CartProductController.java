package com.senior.controllers;

import com.senior.dto.request.CartProductRequest;
import com.senior.dto.response.CartProductResponse;
import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import com.senior.services.CartProductService;
import com.senior.services.CartService;
import com.senior.services.ProductService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("cartProduct")
public class CartProductController {
    @Autowired
    private CartProductService CartProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @PostMapping(path = "/{productId}/{cartId}", produces = "application/json", consumes = "application/json")
    public CartProductResponse create(@Valid @RequestBody CartProductRequest body,
                                      @PathVariable String productId,
                                      @PathVariable String cartId ) {


        Product product = productService.findProductById(productId);
        Cart cart = cartService.findCartById(cartId);

        CartProductResponse cartProductCreated = CartProductService.addProductToCart(body, product, cart);
        return cartProductCreated;

    }


    @GetMapping(path = "/")
    public List<CartProduct> listAllCarts(){
        return this.CartProductService.getAll();
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public void updateCartById(@RequestBody CartProductRequest request, @PathVariable String id){
        this.CartProductService.updateCartById(request, id);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public void deleteCartById(@RequestBody CartProductRequest request, @PathVariable String id) {
        this.CartProductService.deleteCartById(request, id);
    }
}
