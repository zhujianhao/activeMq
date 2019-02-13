package com.zjh;

import com.zjh.clients.Clients;
import com.zjh.connectionManeger.ConnectionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.util.Scanner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
@Component
public class Tests {

    @Autowired
    Clients clients1 ;


    @Test
    public void testClient1() throws InterruptedException {
//        final Clients clients1 = new Clients();
        int i= 1;
        while(true){
            Thread.sleep(1000);

            clients1.sendMessage("T","hello"+i++);
        }


    }

    @Test
    public void talKingBoard() {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:springContext.xml");
//
//        final Clients clients1 =classPathXmlApplicationContext.getBean(Clients.class);
        try {
        while (true) {
            clients1.showMessage("T");
        }
    } catch (JMSException e) {
        e.printStackTrace();
    }
    }



    @Test
    public void testClient2() throws InterruptedException {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:springContext.xml");
//
//        final Clients clients1 =classPathXmlApplicationContext.getBean(Clients.class);
        int i= 1;
        while(true){
            Thread.sleep(1000);

            clients1.sendMessage("T","hello"+i++);
        }


    }






    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }

}
