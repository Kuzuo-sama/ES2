package com.es2.bridge;
public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException() {
        System.out.println("Servide not found");
    }
}
