package com.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//接收者
public class ActiveMqComUser {


    //activemq的账号  都是默认的 可以修改
    private static String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
    //activemq的密码  都是默认的 可以修改
    private static String PASSWORD =ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    //activemq的地址  都是默认的 可以修改
    private static String URL =ActiveMQConnectionFactory.DEFAULT_BROKER_URL;


    public static void main(String[] args) {

        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection;
        //会话
        Session session;
        //转发器(接收者) 在这边叫做接收器
        Destination destination;
        //消息的接收者
        MessageConsumer messageConsumer;

        //创建Ativemq的工厂类
        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);

            try {
                //连接
            connection=connectionFactory.createConnection();
            //启动
            connection.start();
            // 创建session会话 true为保存 false 为不保存   Session.AUTO_ACKNOWLEDGE)  默认采用的是一个确认的原则
            session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //获取 创建队列
            destination=session.createQueue("java");
            //接收 队列里面的消息
            messageConsumer=session.createConsumer(destination);
            while (true){
                //每隔10000毫秒接收一次消息请求
                    TextMessage textMessage = (TextMessage) messageConsumer.receive(10000);
                if (textMessage !=null && textMessage.getText()!=null) {
                    System.out.println(textMessage.getText());
                }else {
                    break;
                }
            }


        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
