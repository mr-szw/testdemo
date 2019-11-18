package com.dawei.test.demo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * @author by Dawei on 2018/8/16.
 * 原生的JMS 消息中件间 生产者
 */
public class QueueProducerDemo {

    private static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;

    //发送消息的条数
    private static final Integer MessageNum = 10;


    public static void messageProducer() {
        //连接工厂
        QueueConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //Session会话
        Session session;
        //消息目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;

        try {
            //1、实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
            //2、通过工厂获取连接
            connection = connectionFactory.createQueueConnection();
            //建立连接
            connection.start();
            //3、通过连接获取会话
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //4、设置消息目的地  -- > 消息队列
            destination = session.createQueue("MessageQueue");
            //5、创建消息生产者， 把消息目的地告知生产者
            messageProducer = session.createProducer(destination);
            //6、发送消息
            sendMessage(session, messageProducer);
            //7、提交消息会话
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /* 发送消息*/
    private static void sendMessage(Session session, MessageProducer messageProducer) {

        int count = 0;
        while (count++ < MessageNum) {
            try {
                TextMessage messageText = session.createTextMessage("Active Message body ===" + count);
                System.out.println("执行消息发送----" + count);
                messageProducer.send(messageText);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


}
