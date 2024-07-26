package com.chatty.practice.suhyeon.pubandsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");


            String logMessage = "log messages";

            // first param "logs" is a default exchange
            // don't need a persistence
            // routeKey "" will be ignored
            // publisher 입장에서는 queue의 존재에 대해 알 필요가 없음
            channel.basicPublish(EXCHANGE_NAME, "", null, logMessage.getBytes());

            System.out.println(" [x] Sent '" + logMessage + "'");



        }
    }
}
