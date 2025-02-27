package com.es2.objectpool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ReusablePool extends Object {
    private static ReusablePool instance;
    private final Set<HttpURLConnection> availableConnections = new HashSet<>();
    private final Set<HttpURLConnection> usedConnections = new HashSet<>();
    private int maxPoolSize = 10; // Tamanho padrão do pool

    private ReusablePool() {}

    // Implementação Singleton
    public static synchronized ReusablePool getInstance() {
        if (instance == null) {
            instance = new ReusablePool();
        }
        return instance;
    }

    // Método para adquirir uma conexão
    public synchronized HttpURLConnection acquire() throws IOException, PoolExhaustedException {
        if (!availableConnections.isEmpty()) {
            HttpURLConnection conn = availableConnections.iterator().next();
            availableConnections.remove(conn);
            usedConnections.add(conn);
            return conn;
        }

        if (usedConnections.size() < maxPoolSize) {
            HttpURLConnection newConn = createNewConnection();
            usedConnections.add(newConn);
            return newConn;
        }

        throw new PoolExhaustedException();
    }

    // Método para liberar uma conexão de volta ao pool
    public synchronized void release(HttpURLConnection conn) throws ObjectNotFoundException {
        if (!usedConnections.contains(conn)) {
            throw new ObjectNotFoundException();
        }
        usedConnections.remove(conn);
        availableConnections.add(conn);
    }

    // Método para resetar o pool
    public synchronized void resetPool() {
        for (HttpURLConnection conn : availableConnections) {
            conn.disconnect();
        }
        for (HttpURLConnection conn : usedConnections) {
            conn.disconnect();
        }
        availableConnections.clear();
        usedConnections.clear();
    }

    // Método para definir o tamanho máximo do pool
    public synchronized void setMaxPoolSize(int size) {
        this.maxPoolSize = size;
    }

    // Criação de uma nova conexão HTTP (apenas para simulação)
    private HttpURLConnection createNewConnection() throws IOException {
        URL url = new URL("http://example.com");
        return (HttpURLConnection) url.openConnection();
    }
}
