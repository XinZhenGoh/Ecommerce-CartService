package com.ecommerce.cartservice.controller;

import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.model.Item;
import com.ecommerce.cartservice.repository.CartRepository;
import com.ecommerce.cartservice.service.CartService;
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

    @Autowired
    CartService service;

//    @GetMapping("/getCartFromUsername/{username}")
//    public List<Cart> getCartByUsername(@PathVariable String username){
//
//        return repository.findAllByUsername(username);
//    }

    @GetMapping("/getCartFromUsername/{username}")
    public Cart getCartByUsername(@PathVariable String username){

        return repository.findCartByUsername(username);
    }

    @GetMapping("/getCart/{id}")
    public Optional<Cart> getCartByID(@PathVariable String id){
        System.out.println("hit");
        return repository.findById(id);
    }

    @PostMapping("/addItem/{username}")
    public ResponseEntity<String> addItem(@RequestBody Item item, @PathVariable String username){
        if(repository.existsByUsername(username)){
            service.add(item,username);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart doesnt exist by username");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cart saved");
    }

    @PostMapping("removeItem/{username}")
    public ResponseEntity<String> decrementOrRemoveItem(@RequestBody Item item, @PathVariable String username){
        if(repository.existsByUsername(username)){
            service.decrementOrRemove(item,username);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart doesnt exist by username");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cart saved");
    }

    @PostMapping("deleteItem/{username}")
    public ResponseEntity<String> removeItem(@RequestBody Item item, @PathVariable String username){
        if(repository.existsByUsername(username)){
            service.remove(item,username);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart doesnt exist by username");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Cart saved");
    }

    @PostMapping("/saveCart")
    public ResponseEntity<String> saveCart(@RequestBody Cart cart){
        String response = "";
        if(repository.existsByUsername(cart.getUsername())){
//            Cart currentCart = repository.findCartByUsername(cart.getUsername());
            response = "Cart already exist, creation ignored";
        }
        else {
            repository.save(cart);
            response = "Cart added";
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
