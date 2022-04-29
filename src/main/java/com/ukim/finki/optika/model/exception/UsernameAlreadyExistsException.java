package com.ukim.finki.optika.model.exception;


public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(){
        super("An user with that username already exists.");
    }
}
