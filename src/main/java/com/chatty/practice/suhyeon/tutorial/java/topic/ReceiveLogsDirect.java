package com.chatty.practice.suhyeon.tutorial.java.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        String randomQueue = channel.queueDeclare().getQueue();

        // binding key has the pattern of "<facility>.<severity>"
        String topic = "service.warn";
        channel.queueBind(randomQueue,EXCHANGE_NAME,topic);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received, severity type : " + delivery.getEnvelope().getRoutingKey()+" , '" + message + "'");
        };

        boolean autoAck = true;
        channel.basicConsume(randomQueue, autoAck, deliverCallback, consumerTag -> { });
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }


}
