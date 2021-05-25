package com.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//发送者
public class ActiveMQ {

    //activemq的账号  都是默认的 可以修改
    private static String USERNAME =ActiveMQConnectionFactory.DEFAULT_USER;
    //activemq的密码 都是默认的 可以修改
    private static String PASSWORD =ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    //activemq的地址   都是默认的 可以修改
    private static String URL =ActiveMQConnectionFactory.DEFAULT_BROKER_URL;



    public static void main(String[] args) {

        ////连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection;
        //会话
        Session session;
        //目标(转发器)
        Destination destination;
        //消息的消费者(发送者)
        MessageProducer messageProducer;

        //连接ActiveMq的工厂类
        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);

        try {
            //创建连接
            connection=connectionFactory.createConnection();
            //启动
            connection.start();
            // 创建session会话 true为保存 false 为不保存   Session.AUTO_ACKNOWLEDGE)  默认采用的是一个确认的原则
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //创建队列  让转发给接收者
            destination=session.createQueue("java");
            //接收者拿到这个对列
            messageProducer=session.createProducer(destination);
            //发送 session数据 和 队列
            sendMessage(messageProducer,session);
            //关闭session commit  是提交  session 提交也就是关闭
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    private static void sendMessage(MessageProducer messageProducer, Session session) throws JMSException {

        for (int i=0;i<10;i++){
            TextMessage textMessage = session.createTextMessage("消息的内容是"+i);

            messageProducer.send(textMessage);

        }



    }


}
