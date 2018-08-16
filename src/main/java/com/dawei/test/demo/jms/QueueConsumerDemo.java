package com.dawei.test.demo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author by Dawei on 2018/8/16.
 * 原生的消息消费者
 */
public class QueueConsumerDemo {

        private static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
        private static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
        private static final String BROKER_URL= ActiveMQConnectionFactory.DEFAULT_BROKER_URL;

        //发送消息的条数
        private static final Integer MessageNum = 10;


        public static void messageConsumer() {
            //连接工厂
            QueueConnectionFactory connectionFactory;
            //连接
            Connection connection = null;
            //Session会话
            Session session;
            //消息目的地
            Destination destination;
            //消息生产者
            MessageConsumer messageConsumer;

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
                //5、创建消息消费者， 把从何出处获取消息信息告诉消费者
                messageConsumer = session.createConsumer(destination);
                //6、接收消息
                receiveMessage(messageConsumer, 1000L);
                //7、提交消息会话
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    /**
    * 接收消息
     * @param messageConsumer  消息消费者
     * @param receiveTime  接收等待时间
    */
    private static void receiveMessage(MessageConsumer messageConsumer, Long receiveTime) {
            //保持阻塞 持续接收处理
            while (true) {
                try {
                    Message messageBody = messageConsumer.receive(receiveTime);
                    if(messageBody != null) {
                        System.out.println("We get message ==== " + messageBody);
                    } else {
                        break;
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
    }
}


