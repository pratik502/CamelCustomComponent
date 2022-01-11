package com.cameldemo.rabbitmq;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.OutputStream;


public class RabbitMqObjectSend {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqObjectSend.class);

    public static void main(String[] args) {
        try {
            Thread.sleep(1*2*1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Inside main RabitMq");
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("./CamelContext.xml");

        ctx.start();
        logger.info("Application context started");

    }


    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            logger.info("Inside handler ---------------------------->");
            String response = "<html><body><h2> Apache Camel Application is up </h2></body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

