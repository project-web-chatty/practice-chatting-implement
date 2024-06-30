package com.chatty.practice.suhyeon.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {


            channel.exchangeDeclare(EXCHANGE_NAME, "direct");


            String logMessage = "warning!!";
            String severity = "warn";


            channel.basicPublish(EXCHANGE_NAME, severity, null, logMessage.getBytes());

            System.out.println(" [x] Sent '" + logMessage + "'");


        }
    }
}
