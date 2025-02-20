package com.es2.factorymethod;

public class UndefinedProductException extends Exception {
    public UndefinedProductException() {
        super("Product type is undefined.");
    }
}
