package com.ecommerce.cartservice.controller;

import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository repository;

    @GetMapping("/{username}")
    public List<Cart> getCartByUsername(@PathVariable String username){

        return repository.findAllByUsername(username);
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCartByID(@PathVariable String id){

        return repository.findById(id);
    }

    @PostMapping("/saveCart")
    public ResponseEntity<String> saveCart(@RequestParam Cart cart){
        repository.save(cart);

        return ResponseEntity.status(HttpStatus.OK).body("Cart saved");
    }
}
