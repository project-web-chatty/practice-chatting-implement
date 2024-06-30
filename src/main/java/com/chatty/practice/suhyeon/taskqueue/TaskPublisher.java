package com.chatty.practice.suhyeon.taskqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;


//publisher
public class TaskPublisher {
    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // socket connection
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // declare queue
            boolean durable = true;
            channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);


            // 1 second per one dot.
            String task = ".......";

            // first param "" is a default exchange
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, task.getBytes());
            System.out.println(" [x] Sent '" + task + "'");




        }
    }
}
