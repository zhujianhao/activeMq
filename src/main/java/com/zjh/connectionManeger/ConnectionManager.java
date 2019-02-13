package com.zjh.connectionManeger;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.language.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.concurrent.ConcurrentHashMap;

@Component("connectionManager")
public class ConnectionManager {

    @Autowired
    private  ActiveMQConnectionFactory factory;

    private static Connection connection;

    public static String default_Key  =  "default";

    private static ConcurrentHashMap<String,Session> sessionMaps = new ConcurrentHashMap<String, Session>();

    public  Connection getConnection() throws JMSException {
        if (null !=connection){
            return connection;
        }else{
            connection=factory.createConnection();
            connection.start();
            return connection;
        }
    }


    //不指定key 使用默认session
    public  Session getSession() throws JMSException {
        return getSession(default_Key);
    }

    //可以根据需要为不通的业务创建不同的session
    public  Session getSession(String key) throws JMSException {
        return getSession(key,true,Session.AUTO_ACKNOWLEDGE);
    }

    //可以根据需要为不通的业务创建不同的session
    public  Session getSession(String key,boolean b) throws JMSException {
        return getSession(key,b,Session.AUTO_ACKNOWLEDGE);
    }
    //可以根据需要为不通的业务创建不同的session
    public  Session getSession(String key,int model) throws JMSException {
        return getSession(key,true,model);
    }

    //可以根据需要为不通的业务创建不同的session
    public  Session getSession(String key,boolean b,int model) throws JMSException {
        String trueKey = key+"_"+b+"_"+model;
        Session session = sessionMaps.get(trueKey);
        if (null !=session){
            return session;
        }else{
            session=getConnection().createSession(b,model);
            sessionMaps.put(key,session);
            return session;
        }
    }



}
