package com.ecommerce.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cartID;
//    @OneToMany(fetch = FetchType.EAGER)
    @Embedded
    List<Item> items;
    PaymentStatus paymentStatus;
    String username;
}
