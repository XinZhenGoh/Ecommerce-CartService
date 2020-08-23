package com.ecommerce.cartservice.repository;

import com.ecommerce.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

    List<Cart> findAllByUsername(String username);

    Cart findCartByUsername(String username);

    Boolean existsByUsername(String username);
}
