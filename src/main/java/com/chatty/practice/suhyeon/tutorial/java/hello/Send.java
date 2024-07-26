package com.chatty.practice.suhyeon.tutorial.java.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


//publisher
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // socket connection
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // declare queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = "Hello World!";

            // pub message to above queue
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            // printout
            System.out.println(" [x] Sent '" + message + "'");




        }
    }
}
