package com.akospeteri.playwright.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class EmbeddedTestServer {
    
    private HttpServer server;
    
    public void start() throws IOException {
        
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/index.html", this::serveIndex);
        server.start();
        
        System.out.println("Embedded server started: http://localhost:8080");
    }
    
    private void serveIndex(HttpExchange exchange) throws IOException {
        
        byte[] response = Files.readAllBytes(Path.of("src/test/resources/demo/index.html"));
        
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        
        exchange.sendResponseHeaders(200, response.length);
        exchange.getResponseBody().write(response);
        exchange.close();
    }
    
    public void stop() {
        if (server != null) {
            System.out.println("Stopping server");
            server.stop(0);
        }
    }
}
