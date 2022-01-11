package com.cameldemo.rabbitmq;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class MainWorker {

    public static void main( String[] args ) throws Exception {

        RabbitMqObjectSend.main(args);
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new RabbitMqObjectSend.MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
