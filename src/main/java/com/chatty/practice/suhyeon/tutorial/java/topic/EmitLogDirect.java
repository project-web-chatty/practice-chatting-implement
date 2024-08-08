package com.chatty.practice.suhyeon.tutorial.java.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // topic exchange delcare
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");


            String logMessage = "service -> warning!!";
            // key pattern is "<facility>.<severity>"
            String facility = "service";
            String severity = "warn";
            String topic = facility + "." + severity;


            channel.basicPublish(EXCHANGE_NAME, topic, null, logMessage.getBytes());

            System.out.println(" [x] Sent to <"+ topic + "> '" + logMessage + "'");


        }
    }
}
