package com.zjh;

import com.zjh.connectionManeger.ConnectionManager;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Consumer {

    @Autowired
    private ConnectionManager connectionManager;

    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<MessageConsumer>();
    AtomicInteger count = new AtomicInteger(0);

    private Session session ;


    public void getMessage(String disname) throws JMSException {
        session = connectionManager.getSession("consumer");

        //disname是主题
        try {
            Destination topic = session.createTopic(disname);
            MessageConsumer consumer = null;
                consumer = session.createConsumer(topic);
                TextMessage msg=(TextMessage)consumer.receive();
                if(msg!=null) {
                    msg.acknowledge();
                    System.out.println(Thread.currentThread().getName()+":"+msg.getText());
                }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
