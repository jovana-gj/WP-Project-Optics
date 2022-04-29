package com.ukim.finki.optika.model.exception;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(){
        super("Shopping cart not found.");
    }
}
