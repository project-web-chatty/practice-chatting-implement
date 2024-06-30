package com.chatty.practice.suhyeon.pubandsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

// pub&sub -> queue binding
public class Subscriber {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        String randomQueue = channel.queueDeclare().getQueue();
        // last param is binding key. if exchange's type is a fanout, it's ignored
        channel.queueBind(randomQueue,EXCHANGE_NAME,"");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };

        boolean autoAck = true;
        channel.basicConsume(randomQueue, autoAck, deliverCallback, consumerTag -> { });
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }


}
