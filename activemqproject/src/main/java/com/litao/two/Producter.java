package com.litao.two;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producter {
    public void  init()
    {

        System.out.println("生产者生产消息开始...");
        try {

            //1.产生连接activemq工厂类
            //连接端口 61616
            ConnectionFactory connectionFactory  =new
                    ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");

            //2.创建Activemq的连接对象
            Connection conn= connectionFactory.createConnection();

            //3.启动连接
            conn.start();

            //4.获取连接的会话对象
            Session session =conn.createSession(false,Session.AUTO_ACKNOWLEDGE);

            //5.创建订阅者模式，订阅通道名称
            Topic topic=session.createTopic("thzmsms");

            //6.创建消息的生产者
            MessageProducer producer= session.createProducer(topic);

            //7.创建消息  createTextMessage  这是文本消息
            TextMessage  msg=session.createTextMessage("activemq消息来了");

            //8.发送消息
            producer.send(msg);

            System.out.println("消息发送完毕");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producter p   =new Producter();
        p.init();
    }
}
