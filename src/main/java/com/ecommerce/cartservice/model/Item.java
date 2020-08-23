package com.ecommerce.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    Long itemID;
    String itemName;
    String imageUrl;
    Double price;
    Integer quantity = 1;

    @ManyToOne(cascade = CascadeType.ALL)
    Cart cart;
}
