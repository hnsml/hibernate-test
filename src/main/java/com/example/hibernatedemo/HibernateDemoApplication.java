package com.example.hibernatedemo;

import com.example.hibernatedemo.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateDemoApplication {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

}
