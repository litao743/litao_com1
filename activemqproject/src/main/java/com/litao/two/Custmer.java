package com.litao.two;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Custmer {
    public void  start()
    {
        try
        {
            System.out.println("消费者启动");
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

            //5.创建点对点的消息列队，通道的名称
            Topic topic=session.createTopic("thzmsms");

            //6.创建消息的消费者
            MessageConsumer consumer= session.createConsumer(topic);

            //7.接受消息

            consumer.setMessageListener(new MessageListener() {

                @Override
                public void onMessage(Message message) {

                    TextMessage txtmessage =(TextMessage)message;

                    try {
                        System.out.println("消费者one接受到的消息为:"+(txtmessage.getText()));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });




        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Custmer c1 = new Custmer();
        c1.start();
    }
}
