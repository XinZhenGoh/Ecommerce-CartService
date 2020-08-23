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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cartID;
//    @OneToMany(fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL)
    List<Item> items;
    String paymentStatus;
    @Column(unique = true)
    String username;
}
