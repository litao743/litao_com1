package com.litao.one;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producter {
    public void init(){
        System.out.println("Producter is init start...");

        try {
            //1.连接activemq工厂类
            ConnectionFactory connectionFactory = new
                    ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");
            //2.创建Activemq的连接对象
            Connection conn=connectionFactory.createConnection();
            //3.启动连接
            conn.start();
            //4.获取连接的会话对象
            Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5.创建点对点的消息队列,通道的名称
            Queue queue=session.createQueue("litao");
            //6.创建消息的生产者
             MessageProducer produce=session.createProducer(queue);
             //7.创建消息  createTextMessage 这是文本消息
            TextMessage msg=session.createTextMessage("activemq消息来了");
            //8.发送消息
            produce.send(msg);
            System.out.println("消息发送完毕");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producter producter =new Producter();
        producter.init();
    }
}
