package com.softuni.gameshop.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public ShoppingCart setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShoppingCart setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public ShoppingCart setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public Double getTotal() {
        double sum = 0;
        for (CartItem cartItem: cartItems) {
            sum += cartItem.getTotal();
        }
        return sum;
    }
}
