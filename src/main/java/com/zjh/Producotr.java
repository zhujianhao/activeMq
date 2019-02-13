package com.zjh;

import com.zjh.connectionManeger.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Component
public class Producotr {


    @Autowired
    private ConnectionManager connectionManager;


    ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();

    private Session session;

    public void sendMessage(String disname, String message){

        try {

            session = connectionManager.getSession("prod");
            Destination topic = session.createTopic(disname);
            //消息生产者
            MessageProducer messageProducer = null;
            if(threadLocal.get()!=null){
                messageProducer = threadLocal.get();
            }else{
                messageProducer = session.createProducer(topic);
            }
                //创建一条消息
                TextMessage msg = session.createTextMessage(Thread.currentThread().getName()+":"+ message);
                System.out.println(Thread.currentThread().getName()+"发送消息："+ message);
                //发送消息
                messageProducer.send(msg);
                //提交事务
                session.commit();
                session.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
