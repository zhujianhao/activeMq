package com.zjh;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/***/
@Service
public class Listener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage mes = (TextMessage)message;
        try {
            System.out.println(mes.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
