package com.socialgx.Brocker;

import com.rabbitmq.client.*;
import com.socialgx.Main;
import com.socialgx.Tools.DataTools;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zaurmac on 2/2/17.
 */
public class Receiver {

    private static String VK_QUEUE_NAME = "vk_engine_queue";

    private static final String METHOD_EXPLORE = "explore";

    public Receiver(){

    }

    public void waitVkMessage() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(VK_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(VK_QUEUE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Consumer consumer = new Consumer() {
            @Override
            public void handleConsumeOk(String s) {

            }

            @Override
            public void handleCancelOk(String s) {

            }

            @Override
            public void handleCancel(String s) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String s, ShutdownSignalException e) {

            }

            @Override
            public void handleRecoverOk(String s) {

            }

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {

                Map p = DataTools.splitQuery(new String(bytes));

                System.out.println(new String(bytes));

                if(p.get("method").equals(METHOD_EXPLORE)){
                    Main.getGraphServer().exploreVKTask(
                            DataTools.objectToInt(p.get("level")),
                            DataTools.objectToInt(p.get("cup_id")),
                            (String) p.get("access_token"),
                            DataTools.objectToInt(p.get("user_id")));
                }

            }
        };

        try {
            channel.basicConsume(VK_QUEUE_NAME, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
