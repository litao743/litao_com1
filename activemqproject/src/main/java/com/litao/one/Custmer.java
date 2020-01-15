package com.litao.one;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Custmer {
    public void start(){
        System.out.println("消费者启动");
        try {
            //1.产生连接activemq的工厂类
            ConnectionFactory connectionFactory=new
                    ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");
            //创建Activemq的连接对象
            Connection conn=connectionFactory.createConnection();
            //3.启动连接
            conn.start();
            //4.获取连接的会话对象
            Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //5.创建点对点的消息队列
            Queue queue=session.createQueue("litao");
            //6.创建消息的消费者
            MessageConsumer consumer=session.createConsumer(queue);
            //7.接受消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage=(TextMessage) message;
                    try{System.out.println("消费者接受到的消息是:"+(textMessage.getText()));}
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
            //8.
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Custmer custmer=new Custmer();
        custmer.start();
    }
}
