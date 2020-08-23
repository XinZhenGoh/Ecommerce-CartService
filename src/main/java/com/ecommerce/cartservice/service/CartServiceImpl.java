package com.ecommerce.cartservice.service;

import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.model.Item;
import com.ecommerce.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repository;

    @Override
    public void add(Item item, String username) {
            Boolean added = false;
            Cart currentCart = repository.findCartByUsername(username);

            List<Item> items = currentCart.getItems();
            for(Item it: items){
                if(it.getItemID().equals(item.getItemID())){
                    //item exist
                    if (it.getQuantity() == null) {
                        it.setQuantity(1);
                    } else {
                        it.setQuantity(it.getQuantity() + 1);
                    }
                    added = true;
                }
            }

            if(!added){
                //item doesnt exist, manual add
                System.out.println("items added");
                items.add(item);
            }

            currentCart.setItems(items);
            repository.save(currentCart);
    }

    @Override
    public void decrementOrRemove(Item item, String username) {
        Boolean removed = false;
        Item temp = null;
        Cart currentCart = repository.findCartByUsername(username);

        List<Item> items = currentCart.getItems();
        for(Item it: items){
            if(it.getItemID().equals(item.getItemID())){
                if(it.getQuantity() == 1 || it.getQuantity() == null){
                    temp = it;
                }
                else {
                    it.setQuantity(it.getQuantity()-1);
                    removed = true;
                }
            }
        }

        if(!removed) {
            items.remove(temp);
        }

        currentCart.setItems(items);
        repository.save(currentCart);
    }

    @Override
    public void remove(Item item, String username) {
        Item temp = null;
        Cart currentCart = repository.findCartByUsername(username);

        List<Item> items = currentCart.getItems();
        for (Item it : items){
            if(item.getItemID().equals(it.getItemID())){
                temp = it;
            }
        }
        items.remove(temp);

        currentCart.setItems(items);
        repository.save(currentCart);
    }
}
