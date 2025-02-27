package com.es2.objectpool;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("A conexão fornecida não pertence ao pool.");
    }
}
