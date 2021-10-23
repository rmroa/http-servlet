package com.rm.http.custom.server;

public class HttpServerRunner {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(9000, 100);
        httpServer.run();
    }
}
