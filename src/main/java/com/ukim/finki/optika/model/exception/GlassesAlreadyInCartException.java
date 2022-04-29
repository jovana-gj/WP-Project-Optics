package com.ukim.finki.optika.model.exception;

public class GlassesAlreadyInCartException extends RuntimeException {
    public GlassesAlreadyInCartException(){
        super("This glasses are already in the shopping cart.");
    }
}
