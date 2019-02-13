package com.zjh.clients;

import com.zjh.Consumer;
import com.zjh.Producotr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;


@Component
public class Clients {

@Autowired
Producotr producotr;

    @Autowired
    Consumer consumer;

    public  void sendMessage(String toppic ,String message){

        producotr.sendMessage(toppic,message);
    }

    public void showMessage(String topic) throws JMSException {

        consumer.getMessage(topic);
    }


}
