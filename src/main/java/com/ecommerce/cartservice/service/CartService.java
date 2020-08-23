package com.ecommerce.cartservice.service;

import com.ecommerce.cartservice.model.Item;

public interface CartService {

    public void add(Item item, String username);

    public void decrementOrRemove(Item item, String username);

    public void remove(Item item, String username);

}
