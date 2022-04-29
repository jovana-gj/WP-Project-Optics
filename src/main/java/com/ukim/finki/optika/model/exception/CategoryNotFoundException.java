package com.ukim.finki.optika.model.exception;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException() {
        super("Category not found!");
    }
}
