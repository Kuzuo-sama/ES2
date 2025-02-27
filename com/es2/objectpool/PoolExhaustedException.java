package com.es2.objectpool;

public class PoolExhaustedException extends Exception {
    public PoolExhaustedException() {
        super("O pool de conexões está esgotado.");
    }
}
